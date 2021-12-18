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
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun AccountSetsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val applyLink = stringResource(id = R.string.account_sets_screen_open_account_set_link)
    val moreInfoLink = stringResource(id = R.string.accounts_sets_screen_more_info_link)
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        CenteredTitleAppBar(
            title = stringResource(id = R.string.account_sets_screen_title),
            navController = navController
        )
        ListItemSeparator()
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(id = R.string.account_sets_screen_description),
                color = Gray200,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.account_sets_screen_open_account_set),
                color = Yellow400,
                modifier = Modifier.clickable { uriHandler.openUri(applyLink) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.account_sets_screen_more_info),
                color = Yellow400,
                modifier = Modifier.clickable { uriHandler.openUri(moreInfoLink) }
            )
        }
    }
}