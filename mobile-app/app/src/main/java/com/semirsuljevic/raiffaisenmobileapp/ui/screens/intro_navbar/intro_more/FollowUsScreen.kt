package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar.intro_more

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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun FollowUsScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Column {
        CenteredTitleAppBar(
            title = stringResource(id = R.string.follow_us_screen_title),
            navController = navController
        )
        ListItemSeparator()
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            BigText(text = stringResource(id = R.string.follow_us_screen_raiffaisen))
            BigText(text = stringResource(id = R.string.follow_us_screen_social))
            BigText(text = stringResource(id = R.string.follow_us_screen_media))
            Text(
                text = stringResource(id = R.string.follow_us_screen_list_title),
                color = White,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            TappableSocialMedia(
                text = stringResource(id = R.string.follow_us_screen_facebook),
                icon = painterResource(id = R.drawable.ic_facebook),
                link = stringResource(id = R.string.follow_us_screen_facebook_url)
            )
            TappableSocialMedia(
                text = stringResource(id = R.string.follow_us_screen_youtube),
                icon = painterResource(id = R.drawable.ic_youtube),
                link = stringResource(id = R.string.follow_us_screen_youtube_url),
            )
            TappableSocialMedia(
                text = stringResource(id = R.string.follow_us_screen_viber),
                icon = painterResource(id = R.drawable.ic_viber),
                link = stringResource(id = R.string.follow_us_screen_viber_url),
            )
            TappableSocialMedia(
                text = stringResource(id = R.string.follow_us_screen_linkedin),
                icon = painterResource(id = R.drawable.ic_linkedin),
                link = stringResource(id = R.string.follow_us_screen_linkedin_url)
            )
            TappableSocialMedia(
                text = stringResource(id = R.string.follow_us_screen_instagram),
                icon = painterResource(id = R.drawable.ic_instagram),
                link = stringResource(id = R.string.follow_us_screen_instagram_url)
            )
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}


@Composable
fun BigText(text: String) {
    Text(
        text = text,
        fontSize = 55.sp,
        color = Gray200,
    )
}

@Composable
fun TappableSocialMedia(
    text: String,
    link: String,
    icon: Painter
) {
    val uriHandler = LocalUriHandler.current
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable { uriHandler.openUri(link) }
    ){
        Image(
            painter = icon,
            contentDescription = text,
            modifier = Modifier
                .width(35.dp)
                .height(55.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            color = White
        )
    }
}