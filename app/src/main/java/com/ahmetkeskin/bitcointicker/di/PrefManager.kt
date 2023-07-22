package com.ahmetkeskin.bitcointicker.di

import android.content.Context
import com.ahmetkeskin.bitcointicker.base.get
import com.ahmetkeskin.bitcointicker.base.set

class PrefManager(context: Context) {

    companion object {
        private const val PREFS = "prefs"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }

    private val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    var eMail: String?
        get() = prefs.get(EMAIL)
        set(value) {
            prefs.set(EMAIL, value)
        }
    var password: String?
        get() = prefs.get(PASSWORD)
        set(value) {
            prefs.set(PASSWORD, value)
        }
}