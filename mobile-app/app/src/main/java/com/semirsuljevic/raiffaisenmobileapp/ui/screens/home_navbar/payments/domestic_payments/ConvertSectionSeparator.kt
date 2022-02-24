package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel

@Composable
fun ConvertSectionSeparator(viewModel: PaymentCreateViewModel) {
    ListSectionSeparator(
        text = stringResource(id = R.string.payment_create_screen_amount),
        actions = {
            Text(
                stringResource(id = R.string.payment_create_screen_convert),
                color = White,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = viewModel.currencyConversion.value,
                onCheckedChange = {
                    viewModel.currencyConversion.value = it
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Yellow400,
                    checkedTrackColor = Yellow200,
                    uncheckedTrackColor = Gray200,
                    uncheckedThumbColor = White,
                )
            )
        }
    )
}