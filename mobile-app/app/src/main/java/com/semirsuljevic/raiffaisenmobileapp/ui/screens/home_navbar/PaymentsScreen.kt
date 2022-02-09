package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black

@Composable
fun PaymentsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold (
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.payments_screen_title),
                navController = navController,
                implyLeading = false
            )
        }
    ){
        Column (
            modifier = Modifier.verticalScroll(scrollState)
        ){
            ListSectionSeparator(text = stringResource(id = R.string.payments_screen_section_standard))
            RMBListItem(
                title = stringResource(id = R.string.payments_screen_item_country),
                icon = Icons.Outlined.CreditCard,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListItemSeparator()
            RMBListItem(
                title = stringResource(id = R.string.payments_screen_item_public),
                icon = Icons.Outlined.Article,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListItemSeparator()
            RMBListItem(
                title = stringResource(id = R.string.payments_screen_item_new_template),
                icon = Icons.Outlined.ReceiptLong,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListSectionSeparator(text = stringResource(id = R.string.payments_screen_section_fast_payments))
            RMBListItem(
                title = stringResource(id = R.string.my_profile_screen_item_pay_contact),
                icon = Icons.Outlined.MobileScreenShare,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListSectionSeparator(text = stringResource(id = R.string.payments_screen_section_my_payments))
            RMBListItem(
                title = stringResource(id = R.string.payments_screen_item_my_templates),
                icon = Icons.Outlined.Feed,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListItemSeparator()
            RMBListItem(
                title = stringResource(id = R.string.payments_screen_section_my_payments),
                icon = Icons.Outlined.Feed,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListItemSeparator()
            RMBListItem(
                title = stringResource(id = R.string.payments_screen_item_long_templates),
                icon = Icons.Outlined.Feed,
                onPressed = {
                    navController.navigate(Screen.ErrorScreen.route)
                }
            )
            ListItemSeparator()
        }
    }
}