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
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ScreenTitle

@Composable
fun PaymentsScreen() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        ScreenTitle(text = stringResource(id = R.string.payments_screen_title))
        ListSectionSeparator(text = stringResource(id = R.string.payments_screen_section_standard))
        RMBListItem(title = stringResource(id = R.string.payments_screen_item_country), icon = Icons.Outlined.CreditCard)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.payments_screen_item_public), icon = Icons.Outlined.Article)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.payments_screen_item_new_template), icon = Icons.Outlined.ReceiptLong)
        ListSectionSeparator(text = stringResource(id = R.string.payments_screen_section_fast_payments))
        RMBListItem(title = stringResource(id = R.string.my_profile_screen_item_pay_contact), icon = Icons.Outlined.MobileScreenShare)
        ListSectionSeparator(text = stringResource(id = R.string.payments_screen_section_my_payments))
        RMBListItem(title = stringResource(id = R.string.payments_screen_item_my_templates), icon = Icons.Outlined.Feed)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.payments_screen_section_my_payments), icon = Icons.Outlined.Feed)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.payments_screen_item_long_templates), icon = Icons.Outlined.Feed)
        ListItemSeparator()
    }
}