package com.ahmetkeskin.bitcointicker.feature.auth.login.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.auth.login.domain.SaveUserSettings
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.domain.Authentication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authentication: Authentication,
    private val saveUserSettings: SaveUserSettings
) : BaseViewModel() {
    fun authentication(userModel: UserModel) = authentication.execute(this, Authentication.Params(userModel))

    fun saveUserSettings(userModel: UserModel) = saveUserSettings.execute(this, SaveUserSettings.Params(userModel))
}
