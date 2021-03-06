package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

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
fun IntroMore(
    navController: NavController
) {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        ScreenTitle(text = stringResource(id = R.string.more_screen_title))
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_tools))
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_calculators),
            icon = Icons.Outlined.Calculate,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_currency_list),
            icon = Icons.Outlined.FeaturedPlayList,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_currency_convertor),
            icon = Icons.Outlined.Money,
            onPressed = {
                navController.navigate(Screen.ErrorScreen.route)
            }
        )
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_about_app))
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
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_onboarding),
            icon = Icons.Outlined.ContactSupport,
            onPressed = {
                navController.navigate(Screen.OnBoardingScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_faq),
            icon = Icons.Outlined.Quiz,
            onPressed = {
                navController.navigate(Screen.FAQScreen.route)
            }
        )
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_about_bank))
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_basic_info),
            icon = Icons.Outlined.Quiz,
            onPressed = {
                navController.navigate(Screen.BasicInformationScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_contact),
            icon = Icons.Outlined.ContactPage,
            onPressed = {
                navController.navigate(Screen.ContactUsScreen.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.more_screen_item_follow_us),
            icon = Icons.Outlined.Subscriptions,
            onPressed = {
                navController.navigate(Screen.FollowUsScreen.route)
            }
        )
        ListItemSeparator()
        Spacer(modifier = Modifier.height(60.dp))
    }
}