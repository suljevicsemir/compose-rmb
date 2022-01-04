package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.StorageManager
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun IntroHome(navController: NavController) {

    var counter: String by remember {
        mutableStateOf("0")
    }


    var tokenValue by remember {
        mutableStateOf("Čekaj nije još")
    }
    //context
    val context = LocalContext.current
    // a coroutine scope
    val scope = rememberCoroutineScope()
    val dataStore = StorageManager(context = context)


//    LaunchedEffect(key1 = "semir") {
//        scope.launch {
//            dataStore.tokenPair.collect {
//                if(it != null) {
//                    counter = it.accessToken.toString()
//                }
//            }
//        }
//    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                          navController.navigate(Screen.LoginScreen.route)
//                    scope.launch {
//                        dataStore.setCounter(counterValue = 12)
//                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Yellow400
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(
                    stringResource(id = R.string.intro_home_button),
                    fontWeight = FontWeight.W600
                )
            }
            Text(text = counter.toString(), color = White)
            Spacer(Modifier.height(100.dp))
//            Text(
//                text = "OE ĆE BIT LOGIN",
//                color = Color.White,
//                modifier = Modifier.clickable {
//                    navController.popCurrentAndPush(Screen.LocationsSearchScreen.route)
//                }
//            )
//            Button(
//                onClick = {
//                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//                    if (!task.isSuccessful) {
//                        Log.w("TAG", "Fetching FCM registration token failed", task.exception)
//                        return@OnCompleteListener
//                    }
//
//                    // Get new FCM registration token
//                    val token = task.result
//                    tokenValue = token
//
//                })
//            }) {
//                Text(text = "Tapni me, tapni")
//            }
//            TextField(
//                value = tokenValue,
//                onValueChange = {},
//                textStyle = TextStyle(color = White)
//            )
        }
    }
}

fun NavController.popCurrentAndPush(route: String) {
    popBackStack(graph.startDestinationId, true)
    graph.setStartDestination(route)
    navigate(route = route)
}