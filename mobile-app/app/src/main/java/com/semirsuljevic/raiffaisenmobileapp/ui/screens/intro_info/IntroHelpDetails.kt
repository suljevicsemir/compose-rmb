package com.semirsuljevic.raiffaisenmobileapp.ui.screens.intro_info

import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.BuildConfig
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.BulletListItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListItemSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import java.text.SimpleDateFormat
import java.util.*


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun IntroHelpDetails(navController: NavController) {
    val context = LocalContext.current
    var versionName by remember {
        mutableStateOf("")
    }
    var versionDate by remember {
        mutableStateOf("")
    }
    LaunchedEffect(Unit) {
        try {
            val packageManager = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageManager.versionName
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = BuildConfig.BUILD_TIME.toLong()
            versionDate = SimpleDateFormat("MMMM", context.resources.configuration.locales[0]).format(calendar.time).substring(0, 3) + " " +calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    val scrollState = rememberScrollState()
    Scaffold (
        backgroundColor = Black,
        topBar = {
            CenteredTitleAppBar(
                title = stringResource(id = R.string.intro_help_details_title),
                navController = navController
            )
        }
    )
    {
        Column(Modifier.verticalScroll(scrollState)) {
            ListItemSeparator()
            Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
                Text(
                    stringResource(id = R.string.intro_help_details_headline),
                    color = White,
                    textAlign = TextAlign.Justify,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                DetailsList()
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    stringResource(id = R.string.intro_help_details_version) + " "  + versionName,
                    color = White
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    stringResource(id = R.string.intro_help_details_version_date) + " "+ versionDate,
                    color = White
                )
            }

        }
    }
}

@Composable
fun DetailsList() {
    Column(Modifier.padding(start = 10.dp, top = 10.dp)) {
        BulletListItem(text = stringResource(id = R.string.intro_help_details_item1))
        BulletListItem(text = stringResource(id = R.string.intro_help_details_item2))
        BulletListItem(text = stringResource(id = R.string.intro_help_details_item3))
    }
}