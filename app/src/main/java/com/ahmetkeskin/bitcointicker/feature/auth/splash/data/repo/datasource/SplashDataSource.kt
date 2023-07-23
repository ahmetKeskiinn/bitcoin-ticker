package com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo.datasource

import com.ahmetkeskin.bitcointicker.di.PrefManager
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import javax.inject.Inject

class SplashDataSource @Inject constructor(
    private val prefManager: PrefManager
) {
    fun getLocalSettings() = UserModel(prefManager.eMail, prefManager.password)
}
