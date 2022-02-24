package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.user.Card
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White

@Composable
fun PaymentAccountInfo(card: Card) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                stringResource(id = R.string.payment_create_screen_debit_label),
                color = White,
                fontSize = 14.sp
            )
            Text(
                card.transactionNumber,
                color = White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    card.balance.toString(),
                    color = White,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    stringResource(id = R.string.payment_create_screen_currency),
                    color = White,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
        IconButton(
            onClick = {

            },
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                tint = Gray200,
                contentDescription = "add card",
            )
        }
    }
}