package com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo

import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel

interface SplashRepository {
    suspend fun getLocalSettings(): UserModel
}