package com.ahmetkeskin.bitcointicker.base.extensions

import java.text.DecimalFormat

const val DOUBLE_PATTERN = "#.##"
fun Double.twoLetterAfterComma(): String {
    val df = DecimalFormat(DOUBLE_PATTERN)
    return df.format(this)
}
