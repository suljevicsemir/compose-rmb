package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun DigitalScreensScreen() {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val moreInfoLink = stringResource(id = R.string.digital_services_more_info_link)
    Column (Modifier.verticalScroll(scrollState)){
        CenteredTitleAppBar(title = stringResource(id = R.string.digital_services_screen_title))
        ListItemSeparator()
        Spacer(modifier = Modifier.height(20.dp))
        Column(Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = stringResource(id = R.string.digital_services_screen_description),
                color = Grey200,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(20.dp))
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


@Composable
fun DigitalServicesList() {
    Column {
        BulletListItem(text = stringResource(id = R.string.digital_services_item1))
        BulletListItem(text = stringResource(id = R.string.digital_services_item2))
        BulletListItem(text = stringResource(id = R.string.digital_services_item3))
        BulletListItem(text = stringResource(id = R.string.digital_services_item4))
        BulletListItem(text = stringResource(id = R.string.digital_services_item5))
        BulletListItem(text = stringResource(id = R.string.digital_services_item6))
        BulletListItem(text = stringResource(id = R.string.digital_services_item7))
    }
}



@Composable
fun BulletListItem(text: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Grey200)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            color = Grey200
        )
    }
}