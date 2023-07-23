package com.ahmetkeskin.bitcointicker.base.extensions

import android.util.Log
import com.ahmetkeskin.bitcointicker.base.DATE_SUFFIX
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

fun getCurrentDate(): String {
    val f = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return f.format(Date())
}

fun getCurrentDateWithSuffix(): String {
    return getCurrentDate()
}

fun getMinusForCurrentDate(minusDate: Int? = 1): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val cal = Calendar.getInstance()
    cal.time = Date()
    if (minusDate != null) {
        cal.add(Calendar.DATE, -minusDate)
    } else {
        cal.add(Calendar.DATE, -1)
    }
    Log.d("TAG", "getMinusForCurrentDate: " + formatter.format(cal.time) + DATE_SUFFIX)
    return formatter.format(cal.time)
}
