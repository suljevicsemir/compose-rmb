package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun IntroHome(navController: NavController) {

    var tokenValue by remember {
        mutableStateOf("Čekaj nije još")
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = "OE ĆE BIT LOGIN",
                color = Color.White,
                modifier = Modifier.clickable {
                    navController.popCurrentAndPush(Screen.LocationsSearchScreen.route)
                }
            )
            Button(
                onClick = {
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new FCM registration token
                    val token = task.result
                    tokenValue = token

                })
            }) {
                Text(text = "Tapni me, tapni")
            }
            TextField(
                value = tokenValue,
                onValueChange = {},
                textStyle = TextStyle(color = White)
            )
        }
    }
}

fun NavController.popCurrentAndPush(route: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(route)
    navigate(route = route)
}