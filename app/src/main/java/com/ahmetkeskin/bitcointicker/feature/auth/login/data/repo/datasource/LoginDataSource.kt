package com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo.datasource

import com.ahmetkeskin.bitcointicker.di.PrefManager
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import javax.inject.Inject

class LoginDataSource @Inject constructor(
    private val prefManager: PrefManager
) {

    fun saveUserSettings(userModel: UserModel) {
        prefManager.eMail = userModel.email
        prefManager.password = userModel.password
    }
}