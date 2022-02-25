package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel

@Composable
fun TransactionNumberField(
    viewModel: PaymentCreateViewModel
) {
    Column (
        modifier = Modifier.padding(end = 10.dp, top = 10.dp, bottom = 10.dp)
    ){
        Row (
            modifier = Modifier.height(55.dp)
        ){
            TextField(
                modifier = Modifier
                    .weight(0.7f)
                    .onFocusEvent { focusState ->

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