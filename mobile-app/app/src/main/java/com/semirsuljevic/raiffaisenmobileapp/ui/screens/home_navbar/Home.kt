package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun Home(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ){

        LazyColumn{
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        "Hi SEMIR!",
                        color = White,
                        fontSize = 20.sp
                    )
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.WidgetManager.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Outlined.Settings, contentDescription ="" , tint = White)
                    }
                }
            }
            item {
               Box(
                   modifier = Modifier.padding(horizontal = 10.dp)
               ) {
                   Column(
                       modifier = Modifier
                           .padding(horizontal = 10.dp, vertical = 10.dp)
                           .clip(RoundedCornerShape(12.dp))
                           .background(color = Gray400)
                   ) {
                       Column (
                           modifier = Modifier
                               .padding(horizontal = 10.dp, vertical = 10.dp)
                       ){
                           Text(
                               stringResource(id =  R.string.home_debit_card_label),
                               color = White,
                               fontSize = 18.sp,
                               fontWeight = FontWeight.W600
                           )
                           Text(
                               "1613000465465465456",
                               color = White,
                               fontSize = 14.sp
                           )
                           Row (
                               verticalAlignment = Alignment.Bottom
                           ){
                               Text(
                                   "12.345,53",
                                   color = White,
                                   fontSize = 40.sp,
                                   fontWeight = FontWeight.W400,
                                   textAlign = TextAlign.End
                               )
                               Spacer(modifier = Modifier.width(10.dp))
                               Text(
                                   "BAM",
                                   color = White,
                                   modifier = Modifier.padding(bottom = 8.dp)
                               )
                           }
                           Row(
                               horizontalArrangement = Arrangement.End,
                               modifier = Modifier.fillMaxWidth()
                           ) {
                               Text(
                                   "Ukupno raspoloživo",
                                   color = White,
                                   fontSize = 12.sp
                               )
                               Text(
                                   "12.345,53",
                                   color = White,
                                   fontSize = 16.sp
                               )
                               Text(
                                   "BAM",
                                   color = White,
                                   fontSize = 14.sp
                               )
                           }
                       }
                   }
               }
            }
            items(count = 20) { prva ->
                Text(
                    "Ovo je prva list brojeva $prva",
                    color = White
                )
            }
            items(200) {
                Text(
                    text = "Ovo je broj $it",
                    color = White
                )
            }
        }
    }
}