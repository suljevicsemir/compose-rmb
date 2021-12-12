package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItemSwitch
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun WidgetManager() {
    Column {
        Box (
            modifier = Modifier.wrapContentSize(align = Alignment.Center)
                )
        {
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Arrow Back", tint = White)
            }

            Text(
                text = stringResource(id = R.string.widget_manager_screen_title),
                color = White,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                    .padding(top = 8.dp)
            )
        }
        ListItemSeparator()
        RMBListItemSwitch(title = stringResource(id = R.string.widget_manager_screen_item_transactions), icon = null) {}
        ListItemSeparator()
        RMBListItemSwitch(title = stringResource(id = R.string.widget_manager_screen_item_report), icon = null) {}
        ListItemSeparator()
    }
}