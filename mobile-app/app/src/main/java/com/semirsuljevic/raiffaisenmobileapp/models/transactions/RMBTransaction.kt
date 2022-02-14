package com.semirsuljevic.raiffaisenmobileapp.models.transactions

import com.semirsuljevic.raiffaisenmobileapp.util.DateHelper
import java.util.*

data class RMBTransaction(
    val name: String,
    val amount: Double,
    val date: Calendar,
    val senderId: String
) {
    fun getDate():String {
        return  ""
    }

    fun getDay(): Int {
        return date.get(Calendar.DAY_OF_MONTH)
    }
    fun getYear(): Int {
        return date.get(Calendar.YEAR)
    }
    fun getMonth(): Int {
        return DateHelper.getTransactionMonth(date.get(Calendar.MONTH))
    }
    fun isIncome(): Boolean {
        return  senderId  != "JA"
    }

    fun getAmount(): String {
        val sign = if(isIncome()) "+" else "-"
        return  sign + amount.toString()
    }

}
