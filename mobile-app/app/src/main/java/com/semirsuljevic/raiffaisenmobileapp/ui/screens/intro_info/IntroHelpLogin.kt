package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200

@Composable
fun IntroHelpLogin(navController: NavController) {
    Column {
        CenteredTitleAppBar(
            title = stringResource(id = R.string.intro_help_login_title),
            navController = navController
        )
        ListItemSeparator()
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.intro_help_login_desc),
            color = Gray200,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}