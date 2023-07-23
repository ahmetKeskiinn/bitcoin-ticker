package com.ahmetkeskin.bitcointicker.base.extensions

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
fun getCurrentDate(): String {
    val f = SimpleDateFormat(DATE_PATTERN)
    return f.format(Date())
}

fun getCurrentDateWithSuffix(): String {
    return getCurrentDate()
}

fun getMinusForCurrentDate(minusDate: Int? = 1): String {
    val formatter = SimpleDateFormat(DATE_PATTERN)
    val cal = Calendar.getInstance()
    cal.time = Date()
    if (minusDate != null) {
        cal.add(Calendar.DATE, -minusDate)
    } else {
        cal.add(Calendar.DATE, -1)
    }
    return formatter.format(cal.time)
}
