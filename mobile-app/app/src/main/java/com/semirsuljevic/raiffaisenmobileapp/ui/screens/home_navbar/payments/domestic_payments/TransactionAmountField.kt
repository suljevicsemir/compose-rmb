package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransactionAmountField(viewModel: PaymentCreateViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = Modifier.padding(end = 10.dp)
    ){
        Row (
            modifier = Modifier.height(55.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                modifier = Modifier
                    .weight(0.7f)
                    .bringIntoViewRequester(bringIntoViewRequester)
                    .onFocusEvent {
                        if (it.isFocused) {
                            coroutineScope.launch {
                                bringIntoViewRequester.bringIntoView()
                            }
                        }
                    },
                value = viewModel.amount.value,
                onValueChange = {
                    viewModel.amount.value = it
                },
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = White
                ),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Black,
                    unfocusedBorderColor = Black,
                    cursorColor = Yellow400,
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
            )
            Box(
                Modifier
                    .height(55.dp)
                    .width(1.dp)
                    .background(color = White)
            )
            Text(
                stringResource(id = R.string.payment_create_screen_currency),
                color = Gray200,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(0.15f),
            )
        }
        Divider(color = Gray200, startIndent = 10.dp)
    }
}