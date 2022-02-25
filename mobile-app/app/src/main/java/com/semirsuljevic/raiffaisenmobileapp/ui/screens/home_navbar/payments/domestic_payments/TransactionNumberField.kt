package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun TransactionNumberField(
    viewModel: PaymentCreateViewModel
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    Column (
        modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
    ){
        Row (
            modifier = Modifier.height(55.dp)
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

                value = viewModel.accountNumber.value,
                onValueChange = {
                    viewModel.accountNumber.value = it
                },
                placeholder = {
                    Text(
                        stringResource(id = R.string.payment_create_screen_account_number_hint),
                        color = Gray200
                    )
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
            IconButton(
                modifier = Modifier.weight(0.15f),
                onClick = {

                },
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        tint = Gray200,
                        contentDescription = "add number",
                    )
                }
            )

        }
        Divider(color = Gray200, startIndent = 10.dp)
    }
}