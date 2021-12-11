package com.semirsuljevic.raiffaisenmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.ui.screens.MoreScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.RaiffaisenMobileAppTheme
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

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
            Scaffold (
                backgroundColor = Color.Black
                    ){
                MoreScreen()
            }
        }
    }
}

@Composable
fun RMBListItem(
    title: String,
    icon: ImageVector
) {
    Row {
        Icon(imageVector = icon, contentDescription = title)
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = title,
            color = White
        )

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