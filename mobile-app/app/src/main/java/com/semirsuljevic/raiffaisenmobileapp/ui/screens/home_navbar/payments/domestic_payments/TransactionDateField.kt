package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments.domestic_payments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Gray200
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.White
import com.semirsuljevic.raiffaisenmobileapp.ui.theme.Yellow400
import com.semirsuljevic.raiffaisenmobileapp.util.DateHelper
import com.semirsuljevic.raiffaisenmobileapp.view_models.PaymentCreateViewModel
import java.util.*

@Composable
fun TransactionDateField(
    viewModel: PaymentCreateViewModel
) {
    val context = LocalContext.current
    Column (
        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp)
    ){
        Row (
            modifier = Modifier.height(35.dp).padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            TransactionDateDisplay(calendar = viewModel.calendar.value, modifier = Modifier.weight(0.7f))
            Box(
                Modifier
                    .height(55.dp)
                    .width(1.dp)
                    .background(color = White)
            )
            Icon(
                imageVector = Icons.Outlined.CalendarToday,
                contentDescription = "",
                tint = Yellow400,
                modifier = Modifier
                    .weight(0.15f)
                    .clickable {
                        MaterialDialog(context).show {
                            datePicker(
                                minDate = viewModel.transactionMinDate,
                                currentDate = viewModel.calendar.value,
                                dateCallback = { dialog, datetime ->
                                    viewModel.setDate(datetime)
                                }
                            )
                        }
                    }
            )
        }
        Divider(
            color = Gray200,
            startIndent = 10.dp,
            modifier = Modifier.padding(end = 10.dp)
        )
    }
}

@Composable
private fun TransactionDateDisplay(
    calendar: Calendar,
    modifier: Modifier
) {
    Text(
        stringResource(id = DateHelper.getTransactionMonth(calendar.get(Calendar.MONTH))) + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR),
        color = White,
        modifier = modifier
    )
}