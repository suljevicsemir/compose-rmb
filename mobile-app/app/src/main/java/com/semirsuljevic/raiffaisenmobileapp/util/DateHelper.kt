package com.semirsuljevic.raiffaisenmobileapp.util

import com.semirsuljevic.raiffaisenmobileapp.R

class DateHelper {

    companion object {
        fun getTransactionMonth(month: Int):Int {
            val resource = when(month + 1) {
                1 -> R.string.months_january
                2 -> R.string.months_february
                3 -> R.string.months_march
                4 -> R.string.months_april
                5 -> R.string.months_may
                6 -> R.string.months_june
                7 -> R.string.months_july
                8 -> R.string.months_august
                9 -> R.string.months_september
                10 -> R.string.months_october
                11 -> R.string.months_november
                else -> R.string.months_december
            }
            return resource
        }
    }
}