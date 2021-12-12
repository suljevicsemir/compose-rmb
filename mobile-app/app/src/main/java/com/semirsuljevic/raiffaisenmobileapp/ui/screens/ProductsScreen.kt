package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ScreenTitle

@Composable
fun ProductsScreen() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        ScreenTitle(text = stringResource(id = R.string.products_screen_title))
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.products_screen_item_pick_account), icon = Icons.Outlined.AccountBalance)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.products_screen_item_credit_cards), icon = Icons.Outlined.CreditCard)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.products_screen_item_credits), icon = Icons.Outlined.Receipt)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.products_screen_item_packages), icon = Icons.Outlined.Inventory2)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.products_screen_item_digital_services), icon = Icons.Outlined.LeakRemove)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.products_screen_item_savings), icon = Icons.Outlined.Savings)
        ListItemSeparator()
    }
}