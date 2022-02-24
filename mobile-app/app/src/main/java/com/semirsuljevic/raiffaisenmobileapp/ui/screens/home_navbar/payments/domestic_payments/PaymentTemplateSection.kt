package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel

@Composable
fun PaymentTemplateSection(viewModel: PaymentCreateViewModel) {
    Column (
        modifier = Modifier.padding(end = 10.dp)
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState ->

                },
            value = viewModel.templateName.value,
            onValueChange = {
                viewModel.onTemplateNameChange(it)
            },
            placeholder = {
                Text(
                    stringResource(id = R.string.payment_create_screen_save_hint),
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
        Divider(
            color = Gray200,
            startIndent = 10.dp
        )
        Text(
            stringResource(id = R.string.payment_create_screen_character_limit),
            color = White,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.VerifiedUser,
                contentDescription = "",
                tint = Yellow400
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                stringResource(id = R.string.payment_create_screen_bottom_label),
                color = Gray200,
                fontSize = 12.sp
            )
        }
    }
}