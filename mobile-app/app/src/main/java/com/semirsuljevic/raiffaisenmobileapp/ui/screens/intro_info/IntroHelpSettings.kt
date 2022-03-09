package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
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
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black

import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun IntroHelpSettings(navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold (
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.intro_help_settings_title),
                navController = navController
            )
        }
    ){
        Column(Modifier.verticalScroll(scrollState)) {
            ListItemSeparator()
            Spacer(Modifier.height(10.dp))
            Column (Modifier.padding(horizontal = 20.dp)){
                Text(
                    text = stringResource(id = R.string.intro_help_settings_desc1),
                    color = Gray200,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.intro_help_settings_desc2),
                    color = White,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold
                )
                SettingsList()
            }

        }
    }
}


@Composable
fun SettingsList() {
    Column (Modifier.padding(start = 10.dp, top = 10.dp)){
        BulletListItem(text = stringResource(id = R.string.intro_help_settings_1))
        BulletListItem(text = stringResource(id = R.string.intro_help_settings_2))
        BulletListItem(text = stringResource(id = R.string.intro_help_settings_3))
    }
}