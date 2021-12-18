package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R

import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ScreenTitle
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen


@Composable
fun MoreScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        ScreenTitle(text = stringResource(id = R.string.more_screen_title))
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_tools))
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_calculators),
            icon = Icons.Outlined.Calculate,
            route = Screen.ErrorScreen.route,
            navController = navController
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_currency_list),
            icon = Icons.Outlined.FeaturedPlayList,
            route = Screen.ErrorScreen.route,
            navController = navController
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_currency_convertor),
            icon = Icons.Outlined.Money,
            route = Screen.ErrorScreen.route,
            navController = navController,
        )
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_about_app))
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_onboarding),
            icon = Icons.Outlined.ContactSupport,
            route = "Screen.In.route",
            navController = navController
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_faq),
            icon = Icons.Outlined.Quiz,
            route = Screen.ErrorScreen.route,
            navController = navController
        )
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_about_bank))
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_basic_info),
            icon = Icons.Outlined.Quiz,
            route = Screen.BasicInformationScreen.route,
            navController = navController
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_contact),
            icon = Icons.Outlined.ContactPage,
            route = Screen.ContactUsScreen.route,
            navController = navController
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_follow_us),
            icon = Icons.Outlined.Subscriptions,
            route = Screen.FollowUsScreen.route,
            navController = navController
        )
        ListItemSeparator()
    }
}