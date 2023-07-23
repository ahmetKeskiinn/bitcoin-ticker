package com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo

import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel

interface LoginRepository {
    fun saveUserSettings(userModel: UserModel)
}
