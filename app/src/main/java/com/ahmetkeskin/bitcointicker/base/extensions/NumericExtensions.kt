package com.ahmetkeskin.bitcointicker.base

import java.text.DecimalFormat

fun Double.twoLetterAfterComma(): String {
    val df = DecimalFormat("#.##")
    return df.format(this)
}
