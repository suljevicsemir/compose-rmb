package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.BulletListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun IntroHelpSecurity(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(Modifier.verticalScroll(scrollState)) {
        CenteredTitleAppBar(
            title = stringResource(id = R.string.intro_help_security_title),
            navController = navController
        )
        ListItemSeparator()
        Column(Modifier.padding(horizontal = 20.dp)) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.intro_help_settings_desc1),
                color = White,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            SecurityList()
        }
    }
}

@Composable
fun SecurityList() {
    Column(Modifier.padding(horizontal = 10.dp)) {
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item1))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item2))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item3))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item4))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item5))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item6))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item7))
        BulletListItem(text = stringResource(id = R.string.intro_help_security_item8))
    }
}