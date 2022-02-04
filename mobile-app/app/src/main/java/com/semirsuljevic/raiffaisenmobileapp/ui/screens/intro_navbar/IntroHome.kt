package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Composable
fun IntroHome(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.TopEnd)
            ) {

                IconButton(
                    modifier = Modifier.fillMaxWidth().wrapContentSize(align = Alignment.TopEnd),
                    onClick = {
                        navController.navigate(Screen.InfoHelp.route)
                    },
                    enabled = true,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Home Info",
                        tint = White,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                  navController.navigate(Screen.LoginScreen.route)
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
            Spacer(Modifier.height(100.dp))
        }
    }
}
