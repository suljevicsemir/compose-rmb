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
import com.semirsuljevic.raiffaisenmobileapp.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ScreenTitle


@Composable
fun MoreScreen() {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.verticalScroll(scrollState)
    ){
        ScreenTitle(text = stringResource(id = R.string.more_screen_title))
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_tools))
        RMBListItem(title = stringResource(id = R.string.more_screen_item_calculators), icon = Icons.Outlined.Calculate)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.more_screen_item_currency_list), icon = Icons.Outlined.FeaturedPlayList)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.more_screen_item_currency_convertor), icon = Icons.Outlined.Money)
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_about_app))
        RMBListItem(title = stringResource(id = R.string.more_screen_item_onboarding), icon = Icons.Outlined.ContactSupport)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.more_screen_item_faq), icon = Icons.Outlined.Quiz)
        ListSectionSeparator(text = stringResource(id = R.string.more_screen_section_about_bank))
        RMBListItem(title = stringResource(id = R.string.more_screen_item_basic_info), icon = Icons.Outlined.Quiz)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.more_screen_item_contact), icon = Icons.Outlined.ContactPage)
        ListItemSeparator()
        RMBListItem(title = stringResource(id = R.string.more_screen_item_follow_us), icon = Icons.Outlined.Subscriptions)
        ListItemSeparator()
    }
}