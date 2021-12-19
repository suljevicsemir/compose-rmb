package com.semirsuljevic.raiffaisenmobileapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.FAQItem
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.ui.view_models.FAQViewModel

@Composable
fun FAQScreen(navController: NavController, viewModel: FAQViewModel) {
    val scrollState = rememberScrollState()
    Column (
        Modifier.fillMaxSize().wrapContentSize(align = Alignment.Center)
    ){
        if(viewModel.loading.value) {
            CircularProgressIndicator(
                color = Yellow400
            )
        }
        else {
            CenteredTitleAppBar(title = stringResource(id = R.string.faq_screen_title), navController = navController)
            Column (
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .verticalScroll(scrollState)
            ){
                FirstDescription(text = stringResource(id = R.string.faq_screen_desc_1))
                Spacer(modifier = Modifier.height(15.dp))
                SecondDescription(text = stringResource(id = R.string.faq_screen_desc_2))
                Spacer(modifier = Modifier.height(15.dp))
                TappableURI(icon = painterResource(id = R.drawable.ic_viber), uri = stringResource(id = R.string.faq_screen_number_1)) {}
                TappableURI(icon = painterResource(id = R.drawable.ic_viber), uri = stringResource(id = R.string.faq_screen_number_2)) {}
                if(viewModel.items.value != null && viewModel.items.value!!.isNotEmpty()) {
                    for (i in viewModel.items.value!!.indices) {
                        FAQListItem(item = viewModel.items.value!![i], index = i)
                    }
                }
                Spacer(modifier = Modifier.height(65.dp))
            }

        }

    }
}

@Composable
fun FirstDescription(text: String) {
    Text(
        text = text,
        color = White,
        fontSize = 20.sp,
    )
}

@Composable
fun SecondDescription(text: String) {
    Text(
        text = text,
        color = White,
        fontSize = 16.sp
    )
}

@Composable
fun FAQListItem(item: FAQItem, index: Int) {
    var tapped by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        Modifier
            .padding(vertical = 10.dp)
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Column (
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    tapped = !tapped
                }
                .animateContentSize { _, _ -> }
                .background(color = Gray400)
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp)
        ){
            Row (
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = (index + 1).toString() + ". " + item.questionEn,
                    color = White,
                    modifier = Modifier.weight(1f)
                )
                if(!tapped) {
                    Icon(imageVector = Icons.Outlined.ArrowDownward, contentDescription = "icon downward", tint = Yellow400)
                }
                else {
                    Icon(imageVector = Icons.Outlined.ArrowUpward, contentDescription = "icon upward", tint = Yellow400)
                }
            }
            if(tapped) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = item.answerEn, color = White)
            }

        }
    }


}