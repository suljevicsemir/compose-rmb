package com.semirsuljevic.raiffaisenmobileapp

import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.semirsuljevic.raiffaisenmobileapp.navigation.Navigator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.IntroNavBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.AppTheme
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


            AppTheme(
                darkTheme = true
            ) {
                CompositionLocalProvider(
                    LocalRippleTheme provides JetNewsRippleTheme
                ) {
                    Scaffold (
                        backgroundColor = MaterialTheme.colors.background,
                        bottomBar = {
                            IntroNavBar(navController = navController)
                        }
                    ){
                        Navigator(navController = navController)
                    }
                    //Navigator(navController = navController)
                }
            }
        }

    }
}
//
//@Composable
//fun BottomBar(navController: NavController) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val screens = listOf(
//        RMBBottomBarItem(
//            route = Screen.IntroHome.route,
//            icon = Icons.Default.Home,
//            index = 0
//        ),
//        RMBBottomBarItem(
//            route = Screen.IntroLocations.route,
//            icon = Icons.Filled.LocationOn,
//            index = 1
//        ),
//        RMBBottomBarItem(
//            route = Screen.IntroProducts.route,
//            icon = Icons.Outlined.Inventory2,
//            index = 2
//        ),
//        RMBBottomBarItem(
//            route = Screen.IntroMore.route,
//            icon = Icons.Outlined.More,
//            index = 3
//        )
//    )
//
//    BottomNavigation (
//        backgroundColor = Gray400
//    ){
//        screens.forEach { introBottomBarItem ->
//            BottomNavigationItem(
//                selected = currentDestination?.hierarchy?.any {
//                    it.route == introBottomBarItem.route
//                } == true,
//                onClick = {
//                    navController.navigate(introBottomBarItem.route) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                },
//                selectedContentColor = Yellow400,
//                unselectedContentColor = Gray200,
//                icon = {
//                    Icon(imageVector = introBottomBarItem.icon, contentDescription = introBottomBarItem.route)
//                }
//            )
//        }
//    }
//
//}
