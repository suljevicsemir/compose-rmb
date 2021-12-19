package com.semirsuljevic.raiffaisenmobileapp.ui.screens.my_profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.*
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen

@Composable
fun MyProfileScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        ScreenTitle(text = stringResource(id = R.string.my_profile_screen_title))
        ListItemSeparator()
        ProfileInfo()
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.my_profile_screen_item_communications),
            icon = Icons.Outlined.ConnectWithoutContact,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListSectionSeparator(text = stringResource(id = R.string.my_profile_screen_section_account_package))
        RMBListItem(
            title = stringResource(id = R.string.my_profile_screen_item_earned_points),
            icon = Icons.Outlined.Inventory,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.my_profile_screen_item_products),
            icon = Icons.Outlined.Ballot,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.my_profile_screen_item_pay_contact),
            icon = Icons.Outlined.MobileScreenShare,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListSectionSeparator(text = stringResource(id = R.string.my_profile_screen_section_settings))
        RMBListItemSwitch(
            title = stringResource(id = R.string.my_profile_screen_item_english),
            icon = Icons.Outlined.Translate
        ) {}
        ListItemSeparator()
        RMBListItemSwitch(
            title = stringResource(id = R.string.my_profile_screen_item_dark_theme),
            icon = Icons.Outlined.DarkMode
        ) {}
        ListItemSeparator()
        RMBListItemSwitch(
            title = stringResource(id = R.string.my_profile_screen_item_hide_balance),
            icon = Icons.Outlined.VisibilityOff
        ) {}
        ListItemSeparator()
        RMBListItemSwitch(
            title = stringResource(id = R.string.my_profile_screen_item_biometrics),
            icon = Icons.Outlined.Fingerprint
        ) {}
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.my_profile_screen_item_pin),
            icon = Icons.Outlined.FiberPin,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListSectionSeparator(text = stringResource(id = R.string.my_profile_screen_section_log_out))
        RMBListItem(
            title = stringResource(id = R.string.my_profile_screen_item_log_out),
            icon = Icons.Outlined.Logout,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListItemSeparator()
        Spacer(modifier = Modifier.height(20.dp))
    }
}