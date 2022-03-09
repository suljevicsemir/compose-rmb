package com.semirsuljevic.raiffaisenmobileapp

import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Navigator
import kotlinx.coroutines.InternalCoroutinesApi
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
        StrictMode.setThreadPolicy(
            ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll() // or .detectAll() for all detectable problems
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        )
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
                    super.onAuthenticationFailed()
                }

                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                }


            }

        )

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = Color.Black)

            val navController = rememberNavController()


            MaterialTheme(

            ) {
                CompositionLocalProvider(
                    LocalRippleTheme provides JetNewsRippleTheme
                ) {
                    Navigator(navController = navController)
                }
            }
        }

    }
}
