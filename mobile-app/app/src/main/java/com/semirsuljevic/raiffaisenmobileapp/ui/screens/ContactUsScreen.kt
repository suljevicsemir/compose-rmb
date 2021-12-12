package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400


@Composable
fun ContactUsScreen() {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val webCallUrl = stringResource(id = R.string.contact_us_screen_web_call_link)
    val bhCalls = stringResource(id = R.string.contact_us_screen_bh_viber)
    val abroadCalls = stringResource(id = R.string.contact_us_screen_abroad_viber)

    val context = LocalContext.current


    Column (
        Modifier.verticalScroll(scrollState)
    ){
        CenteredTitleAppBar(title = stringResource(id = R.string.contact_us_page_title))
        Column (
            Modifier.padding(horizontal = 35.dp)
        ){
            Text(
                text = stringResource(id = R.string.contact_us_screen_bih_calls),
                color = White,
                fontSize = 18.sp
            )
            TappableURI(
                icon = painterResource(id = R.drawable.ic_viber),
                uri = stringResource(id = R.string.contact_us_screen_bh_viber),
                onTapped = {}
            )
            Text(
                text = stringResource(id = R.string.contact_us_screen_abroad_calls),
                color = White,
                fontSize = 18.sp
            )
            TappableURI(
                icon = painterResource(id = R.drawable.ic_viber),
                uri = stringResource(id = R.string.contact_us_screen_abroad_viber),
                onTapped = {}
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Yellow400, textDecoration = TextDecoration.Underline)) {
                        append(stringResource(id = R.string.contact_us_screen_web_call))
                    }
                    withStyle(style = SpanStyle(color = White, fontWeight = FontWeight.Bold)) {
                        append(stringResource(id = R.string.contact_us_screen_web_call_description))
                    }
                },
                modifier = Modifier.clickable { uriHandler.openUri(webCallUrl) }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Info(text = stringResource(id = R.string.contact_us_screen_title_country))
            Info(text = stringResource(id = R.string.contact_us_screen_address))
            Info(text = stringResource(id = R.string.contact_us_screen_postal_number))
            Info(text = stringResource(id = R.string.contact_us_screen_contry))
            Spacer(modifier = Modifier.height(30.dp))
            TappableURI(
                icon = painterResource(id = R.drawable.ic_at),
                uri = stringResource(id = R.string.contact_us_screen_info),
                onTapped = {

                }
            )
            TappableURI(
                icon = painterResource(id = R.drawable.ic_phone),
                uri = stringResource(id = R.string.contact_us_screen_phone_number),
                onTapped = {

                }
            )
            Info(text = stringResource(id = R.string.contact_us_screen_swift))


        }

    }
}

@Composable
fun Info(text: String) {
    Text(
        text = text,
        color = White
    )
}


@Composable
fun TappableURI(
    icon: Painter,
    uri: String,
    onTapped: () -> Unit
    ) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onTapped }
            .padding(vertical = 10.dp)
    ){
        Image(
            painter = icon,
            contentDescription = uri,
            modifier = Modifier
                .width(35.dp)
                .height(55.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = uri,
            textDecoration = TextDecoration.Underline,
            fontSize = 16.sp,
            color = Yellow400
        )
    }
}