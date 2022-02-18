package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel


@Composable
fun TransactionUrgentField(
    viewModel: PaymentCreateViewModel
) {
    Column (
        modifier = Modifier.padding(horizontal = 10.dp)
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(id = R.string.payment_create_screen_emergency_label),
                color = White
            )
            Switch(
                checked = viewModel.urgent.value,
                onCheckedChange = {
                    viewModel.urgent.value = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Yellow400,
                    checkedTrackColor = Yellow200,
                    uncheckedTrackColor = Gray200,
                    uncheckedThumbColor = White,
                )
            )
        }
        Divider(color = Gray200)
    }
}