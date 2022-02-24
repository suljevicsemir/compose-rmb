package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.CenteredTitleAppBar
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel

@Composable
fun PaymentTopBar(
    navController: NavController,
    viewModel: PaymentCreateViewModel
) {
    CenteredTitleAppBar(
        title = stringResource(id = R.string.payment_create_screen_title),
        navController = navController,
        actions = {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Info",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        viewModel.setDialogState(true)
                    },
                tint = Gray200,
            )
        }
    )
}