package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.RMBListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun IntroHelpScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column(Modifier.verticalScroll(scrollState)) {
        CenteredTitleAppBar(
            title = stringResource(id = R.string.intro_help_screen_title),
            navController = navController
        )
        Column (Modifier.padding(horizontal = 20.dp)){
            Text(
                text = stringResource(id = R.string.intro_help_screen_desc1),
                color = White,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.intro_help_screen_desc2),
                color = Gray200,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
        RMBListItem(
            title = stringResource(id = R.string.intro_help_screen_login),
            icon = Icons.Outlined.Login,
            onPressed = {
                navController.navigate(Screen.InfoHelpLogin.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.intro_help_screen_settings),
            icon = Icons.Outlined.Settings,
            onPressed = {
                navController.navigate(Screen.InfoHelpSettings.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.intro_help_screen_lang),
            icon = Icons.Outlined.Translate,
            onPressed = {
                navController.navigate(Screen.InfoHelpLanguage.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.intro_help_screen_security),
            icon = Icons.Outlined.Security,
            onPressed = {
                navController.navigate(Screen.InfoHelpSecurity.route)
            }
        )
        ListItemSeparator()
        RMBListItem(
            title = stringResource(id = R.string.intro_help_screen_tehnical),
            icon = Icons.Outlined.Description,
            onPressed = {
                navController.navigate(Screen.InfoHelpDetails.route)
            }
        )
        ListItemSeparator()
    }
}
