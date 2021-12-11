package com.semirsuljevic.raiffaisenmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.MoreScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Grey200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.RaiffaisenMobileAppTheme
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            RaiffaisenMobileAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
            MaterialTheme(

            ) {
                CompositionLocalProvider(
                    LocalRippleTheme provides JetNewsRippleTheme
                ) {
                    Scaffold (
                        backgroundColor = Color.Black
                    ){
                        MoreScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun RMBListItem(
    title: String,
    icon: ImageVector
) {
    Box(modifier = Modifier.clickable {

    }) {
        Row (
            Modifier.fillMaxWidth().padding(all = 12.dp)

        ){
            Icon(imageVector = icon, contentDescription = title, tint = Grey200, modifier = Modifier.size(26.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                color = White,
                fontSize = 17.sp
            )

        }
    }
}

@Composable
fun RMBListItemSwitch(
    title: String,
    icon: ImageVector,
    onChanged: () -> Unit
) {
    Row {
        Icon(imageVector = icon, contentDescription = title)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = title, color = White)
        Spacer(modifier = Modifier.weight(1f))
        Switch(checked = true, onCheckedChange = {

        })

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RaiffaisenMobileAppTheme {
        Greeting("Android")
    }
}