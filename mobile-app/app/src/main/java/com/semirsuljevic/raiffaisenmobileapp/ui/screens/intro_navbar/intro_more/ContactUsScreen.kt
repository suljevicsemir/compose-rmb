package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_more

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.LaunchableEmailAddress
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.LaunchablePhoneNumber
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400


@Composable
fun ContactUsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    val webCallUrl = stringResource(id = R.string.contact_us_screen_web_call_link)

    Column (
        Modifier.verticalScroll(scrollState)
    ){
        CenteredTitleAppBar(
            title = stringResource(id = R.string.contact_us_page_title),
            navController = navController
        )
        ListItemSeparator()
        Column (
            Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
        ){
            Text(
                text = stringResource(id = R.string.contact_us_screen_bih_calls),
                color = White,
                fontSize = 18.sp
            )
            LaunchablePhoneNumber(
                icon = painterResource(id = R.drawable.ic_viber),
                phoneNumber = stringResource(id = R.string.contact_us_screen_bh_viber),
            )
            Text(
                text = stringResource(id = R.string.contact_us_screen_abroad_calls),
                color = White,
                fontSize = 18.sp
            )
            LaunchablePhoneNumber(
                icon = painterResource(id = R.drawable.ic_viber),
                phoneNumber = stringResource(id = R.string.contact_us_screen_abroad_viber)
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
            LaunchableEmailAddress(
                icon = painterResource(id = R.drawable.ic_at),
                emailAddress = stringResource(id = R.string.contact_us_screen_info),
            )
            LaunchablePhoneNumber(
                icon = painterResource(id = R.drawable.ic_phone),
                phoneNumber = stringResource(id = R.string.contact_us_screen_phone_number),
            )
            Info(text = stringResource(id = R.string.contact_us_screen_swift))
            Spacer(modifier = Modifier.height(60.dp))

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

