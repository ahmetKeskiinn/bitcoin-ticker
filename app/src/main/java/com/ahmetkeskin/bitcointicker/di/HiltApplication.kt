package com.ahmetkeskin.bitcointicker.di

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.ahmetkeskin.bitcointicker.base.crash.CrashActivity
import com.ahmetkeskin.bitcointicker.feature.auth.AuthActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CaocConfig.Builder.create()
            .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
            .enabled(true)
            .showErrorDetails(false)
            .showRestartButton(false)
            .logErrorOnRestart(false)
            .trackActivities(true)
            .restartActivity(AuthActivity::class.java)
            .errorActivity(CrashActivity::class.java)
            .minTimeBetweenCrashesMs(500) // default: 3000
            .errorDrawable(com.ahmetkeskin.bitcointicker.R.drawable.ic_favorite)
            .apply()
    }
}
