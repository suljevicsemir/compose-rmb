package com.semirsuljevic.raiffaisenmobileapp

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.semirsuljevic.raiffaisenmobileapp.navigation.Navigator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.HomeNavBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.IntroNavBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.AppTheme
import com.semirsuljevic.raiffaisenmobileapp.view_models.AppHelperViewModel
import com.semirsuljevic.raiffaisenmobileapp.view_models.SecureSharedPref
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*
import java.util.concurrent.Executor


private object JetNewsRippleTheme : RippleTheme {
    // Here you should return the ripple color you want
    // and not use the defaultRippleColor extension on RippleTheme.
    // Using that will override the ripple color set in DarkMode
    // or when you set light parameter to false
    @Composable
    override fun defaultColor(): Color = Color.White

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.White,
        lightTheme = !isSystemInDarkTheme()
    )
}
@ExperimentalPagerApi
class MainActivity : FragmentActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { updateBaseContextLocale(
            context = it
        ) })
    }

    private fun updateBaseContextLocale(context: Context): Context? {
        val lang = SecureSharedPref(context = context).getStringValue("lang")
        val language: String = lang ?: "en"
        val locale = Locale(language)
        Locale.setDefault(locale)
        return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            updateResourcesLocale(context, locale)
        } else updateResourcesLocaleLegacy(context, locale)
    }

    @TargetApi(Build.VERSION_CODES.N_MR1)
    private fun updateResourcesLocale(context: Context, locale: Locale): Context? {
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLocaleLegacy(context: Context, locale: Locale): Context? {
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }



    private val authenticationCallback: BiometricPrompt.AuthenticationCallback =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@MainActivity, "Authentication Succeeded", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@MainActivity, "Authentication Error code: $errorCode", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
            }

            override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                super.onAuthenticationHelp(helpCode, helpString)
            }
        }

    @OptIn(InternalCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.P)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val executor: Executor = ContextCompat.getMainExecutor(this)

        val prompt = androidx.biometric.BiometricPrompt(
            this,
            executor,
            object: androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationFailed() {
                    println("debug: Authentication failed")
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    println("debug: Biometrija uspjela ćitaba mi")
                    super.onAuthenticationSucceeded(result)
                }


            }

        )

        val promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()






        setContent {

            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = Color.Black)
            val x = remember {
                mutableStateOf(false)
            }

            val biometricManager = androidx.biometric.BiometricManager.from(LocalContext.current)
            when(biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
                BiometricManager.BIOMETRIC_SUCCESS ->
                    Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                    Log.e("MY_APP_TAG", "No biometric features available on this device.")
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                    Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    Log.e("MY_APP_TAG", "NOT ENROLLED")
                    // Prompts the user to create credentials that your app accepts.
                }
            }

            x.value
            //updateBaseContextLocale(LocalContext.current)
            val navController = rememberNavController()
            val context = LocalContext.current
            val configuration = LocalConfiguration.current
            val appStateViewModel : AppHelperViewModel = viewModel(LocalContext.current as FragmentActivity)
            x.value
            AppTheme(
                darkTheme = true
            ) {
                CompositionLocalProvider(
                    LocalRippleTheme provides JetNewsRippleTheme
                ) {
                    Scaffold (
                        floatingActionButton = {
                            Button(onClick = {
//                                SecureSharedPref(context = context).storeStringValue("lang", "bs")
//                                recreate()
                                prompt.authenticate(promptInfo)
                                x.value = true
                            }) {
                                Text(text = "click me")
                            }
                        },
                        backgroundColor = MaterialTheme.colors.background,
                        bottomBar = {
                            if(appStateViewModel.isIntroNavBar.value) {
                                IntroNavBar(navController = navController)
                            }
                            else {
                                HomeNavBar(navController = navController)
                            }
                        }
                    ){
                        Navigator(navController = navController)
                    }
                }
            }
        }

    }
}

//    @SuppressLint("NewApi")
//    fun checkForBiometrics() : Boolean{
//        Log.d(TAG, "checkForBiometrics started")
//        var canAuthenticate = true
//        if (Build.VERSION.SDK_INT < 29) {
//            val keyguardManager : KeyguardManager = applicationContext.getSystemService(KEYGUARD_SERVICE) as KeyguardManager
//            val packageManager : PackageManager = applicationContext.packageManager
//            if(!packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
//                Log.w(TAG, "checkForBiometrics, Fingerprint Sensor not supported")
//                canAuthenticate = false
//            }
//            if (!keyguardManager.isKeyguardSecure) {
//                Log.w(TAG, "checkForBiometrics, Lock screen security not enabled in Settings")
//                canAuthenticate = false
//            }
//        } else {
//            val biometricManager : BiometricManager = this.getSystemService(BiometricManager::class.java)
//            if(biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) != BiometricManager.BIOMETRIC_SUCCESS){
//                Log.w(TAG, "checkForBiometrics, biometrics not supported")
//                canAuthenticate = false
//            }
//        }
//        Log.d(TAG, "checkForBiometrics ended, canAuthenticate=$canAuthenticate ")
//        return canAuthenticate
//    }


