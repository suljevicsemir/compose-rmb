package com.semirsuljevic.raiffaisenmobileapp.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class PaymentCreateViewModel:ViewModel() {

    private val _templateName = mutableStateOf("")
    private val _isDialogOpened = mutableStateOf(false)
    private val _currencyConversion = mutableStateOf(false)

    val fields = FieldsController()

    fun setDialogState(value: Boolean) {
        _isDialogOpened.value = value
    }

    fun setCurrencyConversion(value: Boolean) {
        _currencyConversion.value = value
    }


    fun onTemplateNameChange(value: String) {
        _templateName.value = value
    }

    val templateName: MutableState<String> = _templateName
    val isDialogOpened: MutableState<Boolean> = _isDialogOpened
    val currencyConversion: MutableState<Boolean> = _currencyConversion
}

class FieldsController {
    var x = mutableStateOf("")
    var dateLabel = mutableStateOf("")
    var templateName = mutableStateOf("")
    var accountNumber = mutableStateOf("")
    var amount = mutableStateOf<Float>(0f)
        private set

    fun setDateFromPicker(calendar: Calendar) {
        dateLabel.value = calendar.get(Calendar.DAY_OF_MONTH).toString() + ", " + calendar.get(Calendar.YEAR)
    }

    fun setAmount(value: String):Boolean {
        return if(value.toFloatOrNull() == null) false else {
            amount.value = value.toFloat()
            true
        }
    }


}