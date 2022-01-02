package com.semirsuljevic.raiffaisenmobileapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.IntroNavbar
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Call
import retrofit2.http.GET

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
class MainActivity : ComponentActivity() {



    @OptIn(InternalCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.N)

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = Color.Black)
            MaterialTheme(

            ) {
                CompositionLocalProvider(
                    LocalRippleTheme provides JetNewsRippleTheme
                ) {
                    IntroNavbar()
                }
            }
        }

    }
}

interface MyAPI {
    @GET("/comments")
    fun getComments(): Call<List<com.semirsuljevic.raiffaisenmobileapp.Comment>>
}


data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)