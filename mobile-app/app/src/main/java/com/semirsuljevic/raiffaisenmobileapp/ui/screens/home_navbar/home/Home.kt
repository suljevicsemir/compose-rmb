package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun Home(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
        ){
            item {
                HomeTopBar(navController = navController)
            }
            item {
               HomeAccountBalance()
            }

            items(5) { item ->
                when(item) {
                    0 -> HomeTransactions()
                    1 -> Text("Caaaaaampeeeeerrrrr", color = White)
                    2 -> Text("Caaaaaampeeeeerrrrr")
                    3 -> Text("Caaaaaampeeeeerrrrr")
                    4 -> Text("Caaaaaampeeeeerrrrr")

                }
            }
        }
    }
}