package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItemSwitch

@Composable
fun WidgetManager() {
    Column {
        CenteredTitleAppBar(title = stringResource(id = R.string.widget_manager_screen_title))
        ListItemSeparator()
        RMBListItemSwitch(title = stringResource(id = R.string.widget_manager_screen_item_transactions), icon = null) {}
        ListItemSeparator()
        RMBListItemSwitch(title = stringResource(id = R.string.widget_manager_screen_item_report), icon = null) {}
        ListItemSeparator()
    }
}