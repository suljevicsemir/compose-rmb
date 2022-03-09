package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_navbar

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.navigation.Screen
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.*


@InternalCoroutinesApi
@Composable
fun IntroHome(navController: NavController) {

    val context = LocalContext.current
    var imageLoader by remember {
        mutableStateOf<ImageLoader?>(value = null)
    }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            imageLoader = ImageLoader.Builder(context = context).componentRegistry {
                if(Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(context = context))
                }
                else {
                    add(GifDecoder())
                }
            }.build()
        }
    }

    if(imageLoader == null) {
        CircularProgressIndicator(
            color = Yellow400
        )
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = getGifPath(),
                    imageLoader = imageLoader!!
                ),
                "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillHeight
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.TopEnd)
                    .align(Alignment.Center)
                    .padding(end = 5.dp, top = 10.dp)

            ) {

                IconButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.TopEnd),
                    onClick = {
                        navController.navigate(Screen.InfoHelp.route)
                    },
                    enabled = true,
                ) {
                    Box(
                        Modifier
                            .align(Alignment.TopEnd)
                            .clip(RoundedCornerShape(48.dp))
                            .size(48.dp)
                            .background(White))
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Home Info",
                        tint = Gray400,
                        modifier = Modifier
                            .background(color = White)
                            .size(36.dp)
                    )

                }

            }

            Column (Modifier.align(alignment = Alignment.BottomCenter)){
                Button(
                    onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Yellow400
                    ),
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .wrapContentSize(align = Alignment.BottomCenter)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 14.dp)
                ) {
                    Text(
                        stringResource(id = R.string.intro_home_button),
                        fontWeight = FontWeight.W600
                    )
                }
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }



}


private fun getGifPath(calendar: Calendar = Calendar.getInstance()): Int {
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)

    //spring
    if((dayOfMonth >= 20 && month == 3) || month == 4 || month == 5 || (month == 6 && dayOfMonth < 20)) {
        return  R.drawable.spring_gif
    }
    //summer
    else if((dayOfMonth >= 20 && month == 6) || month == 7 || month == 8 || (month == 9 && dayOfMonth < 20)) {
        return R.drawable.summer_gif
    }
    //autumn/fall
    else if((dayOfMonth >= 20 && month == 9) || month == 10 || month == 11 || (month == 12 && dayOfMonth < 20)) {
        return  R.drawable.fall_gif
    }
    return R.drawable.winter_gif
}