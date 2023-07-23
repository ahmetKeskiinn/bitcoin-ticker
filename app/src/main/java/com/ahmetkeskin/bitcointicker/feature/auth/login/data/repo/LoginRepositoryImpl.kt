package com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo

import com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo.datasource.LoginDataSource
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
) : LoginRepository {
    override fun saveUserSettings(userModel: UserModel) = dataSource.saveUserSettings(userModel)

}