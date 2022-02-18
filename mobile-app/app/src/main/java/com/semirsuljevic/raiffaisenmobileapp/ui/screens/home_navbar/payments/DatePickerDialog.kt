package com.semirsuljevic.raiffaisenmobileapp.ui.screens.home_navbar.payments

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun PaymentDatePicker() {
    val date = remember {
        mutableStateOf("")
    }

    val calendar = Calendar.getInstance()
    val year1 = calendar.get(Calendar.YEAR)
    val  month1 = calendar.get(Calendar.MONTH)
    val day1 = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        },
        year1,
        month1,
        day1
    )





}