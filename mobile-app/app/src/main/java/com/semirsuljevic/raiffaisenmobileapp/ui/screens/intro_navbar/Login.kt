package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.navigation.AppScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.*
import com.semirsuljevic.raiffaisenmobileapp.view_models.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(title = "", navController = navController)
        }
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 20.dp)

        ){
            Spacer(Modifier.weight(1f ))
            Text(
                stringResource(id = R.string.login_screen_headline),
                color = White,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            LoginSection(loginViewModel = loginViewModel)
            Spacer(Modifier.weight(1f ))
            Button(
                onClick = {
                    coroutineScope.launch {
                        val x = loginViewModel.onLogin()
                        if(x) {
                            navController.navigate(AppScreen.UserHome.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = true
                                }
                            }
                        }
                    }
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
            Spacer(modifier = Modifier.height(80.dp))
        }    
    }
}

@Composable
fun LoginSection(loginViewModel: LoginViewModel) {
    val email by loginViewModel.email.observeAsState("")
    val password by loginViewModel.password.observeAsState("")
    Column (
        modifier = Modifier
            .clip(shape = Shapes.medium)
            .background(color = Gray400)
            .padding(all = 40.dp)
            .fillMaxWidth()

    ){
        Text(
            text = stringResource(id = R.string.login_screen_email),
            color = White,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        LoginTextField(
            value = email,
            onValueChange = {
                loginViewModel.onEmailChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            visualTransformation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.login_screen_password),
            color = White,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        LoginTextField(
            value = password,
            onValueChange = {
                loginViewModel.onPasswordChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
    }
}


@Composable
fun LoginTextField(
    value: String,
    onValueChange: (text: String) -> Unit,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation
) {

    BasicTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            color = White,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(color = Black)
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 0.dp),
        cursorBrush = Brush.verticalGradient(
            0.0f to Yellow400,
            1.0f to Yellow400,
            startY = 0.0f,
            endY = 100.0f
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
    )
}