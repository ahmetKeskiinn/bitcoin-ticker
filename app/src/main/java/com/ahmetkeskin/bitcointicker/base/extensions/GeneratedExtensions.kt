package com.ahmetkeskin.bitcointicker.base.extensions

import android.content.res.Resources

fun Int.toDp(): Int {
    return (this / Resources.getSystem().displayMetrics.density).toInt()
}

fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}
