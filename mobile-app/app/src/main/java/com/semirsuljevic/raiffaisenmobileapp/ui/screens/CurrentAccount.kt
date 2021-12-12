package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Grey200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun CurrentAccountScreen() {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current

    val openAccountOnline = stringResource(id = R.string.current_screen_open_account_link)
    val moreInfo = stringResource(id = R.string.current_account_more_info_link)

    Column (
        Modifier.verticalScroll(scrollState)
    ){
        CenteredTitleAppBar(title = stringResource(id = R.string.current_account_title))
        ListItemSeparator()
        Spacer(modifier = Modifier.height(10.dp))
        Column (
            Modifier.padding(horizontal = 20.dp)
        ){
            Text(
                text = stringResource(id = R.string.current_account_description),
                color = Grey200,
                textAlign =  TextAlign.Justify,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.current_screen_open_account_online),
                color = Yellow400,
                modifier = Modifier.clickable {
                    uriHandler.openUri(openAccountOnline)
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.current_screen_more_info),
                color = Yellow400,
                modifier = Modifier.clickable {
                    uriHandler.openUri(moreInfo)
                }
            )
        }
    }
}