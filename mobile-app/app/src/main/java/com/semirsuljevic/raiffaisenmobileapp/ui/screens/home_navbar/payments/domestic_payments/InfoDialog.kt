package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray400
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400

@Composable
fun InfoDialog(
    isOpened: Boolean,
    onDismissRequest: () -> Unit
) {
    if(isOpened) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            backgroundColor = Gray400,
            buttons = {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismissRequest,
                        content = {
                            Text(
                                stringResource(id = R.string.payment_create_screen_info_dialog_ok),
                                color = Yellow400
                            )
                        },
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 5.dp)
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                }
            },
            text = {
                Column {
                    Text(
                        stringResource(id = R.string.payment_create_screen_info_dialog_text1),
                        color = White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        stringResource(id = R.string.payment_create_screen_info_dialog_text2),
                        color = White
                    )
                }
            }
        )
    }
}