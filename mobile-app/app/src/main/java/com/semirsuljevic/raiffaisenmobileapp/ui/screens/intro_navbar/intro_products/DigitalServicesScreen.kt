package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_products

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.BulletListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun DigitalScreensScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val moreInfoLink = stringResource(id = R.string.digital_services_more_info_link)
    Scaffold (
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.digital_services_screen_title),
                navController = navController
            )
        }
    ){
        Column (Modifier.verticalScroll(scrollState)){
            ListItemSeparator()
            Spacer(modifier = Modifier.height(20.dp))
            Column(Modifier.padding(horizontal = 20.dp)) {
                Text(
                    text = stringResource(id = R.string.digital_services_screen_description),
                    color = Gray200,
                    textAlign = TextAlign.Justify
                )
                Spacer(modifier = Modifier.height(10.dp))
                DigitalServicesList()
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(id = R.string.digital_services_more_info),
                    color = Yellow400,
                    modifier = Modifier.clickable { uriHandler.openUri(moreInfoLink) }
                )
            }
        }
    }
}


@Composable
fun DigitalServicesList() {
    Column (
        modifier = Modifier.padding(horizontal = 10.dp)
    ){
        BulletListItem(text = stringResource(id = R.string.digital_services_item1))
        BulletListItem(text = stringResource(id = R.string.digital_services_item2))
        BulletListItem(text = stringResource(id = R.string.digital_services_item3))
        BulletListItem(text = stringResource(id = R.string.digital_services_item4))
        BulletListItem(text = stringResource(id = R.string.digital_services_item5))
        BulletListItem(text = stringResource(id = R.string.digital_services_item6))
        BulletListItem(text = stringResource(id = R.string.digital_services_item7))
    }
}

