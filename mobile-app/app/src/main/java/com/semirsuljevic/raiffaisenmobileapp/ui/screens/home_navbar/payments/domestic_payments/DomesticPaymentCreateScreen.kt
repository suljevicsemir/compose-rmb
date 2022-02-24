package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semirsuljevic.raiffaisenmobileapp.R
import com.semirsuljevic.raiffaisenmobileapp.models.user.Card
import com.semirsuljevic.raiffaisenmobileapp.ui.composables.ListSectionSeparator
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Black
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel


@ExperimentalComposeUiApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DomesticPaymentCreateScreen(
    navController: NavController,
    viewModel: PaymentCreateViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scrollState = rememberScrollState()

    InfoDialog(isOpened = viewModel.isDialogOpened.value) {
        viewModel.setDialogState(false)
    }

    Scaffold (
        backgroundColor = Black,
        topBar = {
            PaymentTopBar(navController = navController, viewModel = viewModel)
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_from_account))
            PaymentAccountInfo(card = Card(transactionNumber = "161300008855564654", balance = 12345.51))
            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_to_account))

            TransactionNumberField(viewModel = viewModel)

            ConvertSectionSeparator(viewModel = viewModel)

            TransactionAmountField(viewModel = viewModel)
            TransactionUrgentField(viewModel = viewModel)

            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_date))
            TransactionDateField(viewModel = viewModel)

            ListSectionSeparator(text = stringResource(id = R.string.payment_create_screen_save_template))

            PaymentTemplateSection(viewModel = viewModel)

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

