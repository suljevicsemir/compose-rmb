package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.navigation.AppScreen
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ScreenTitle

@Composable
fun IntroProductsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top
    ){
        ScreenTitle(text = stringResource(id = R.string.products_screen_title))
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.products_screen_item_pick_account),
            icon = Icons.Outlined.AccountBalance,
            onPressed = {
                navController.navigate(AppScreen.CurrentAccountScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.products_screen_item_credit_cards),
            icon = Icons.Outlined.CreditCard,
            onPressed = {
                navController.navigate(AppScreen.CreditCardsScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.products_screen_item_credits),
            icon = Icons.Outlined.Receipt,
            onPressed = {
                navController.navigate(AppScreen.LoansScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.products_screen_item_packages),
            icon = Icons.Outlined.Inventory2,
            onPressed = {
                navController.navigate(AppScreen.AccountSetsScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.products_screen_item_digital_services),
            icon = Icons.Outlined.LeakRemove,
            onPressed = {
                navController.navigate(AppScreen.DigitalServicesScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.products_screen_item_savings),
            icon = Icons.Outlined.Savings,
            onPressed = {
                navController.navigate(AppScreen.SavingsScreen.route)
            }
        )
        ListItemSeparator()
    }
}