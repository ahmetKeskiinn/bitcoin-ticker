package com.ahmetkeskin.bitcointicker.feature.auth.splash.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.domain.Authentication
import com.ahmetkeskin.bitcointicker.feature.auth.splash.domain.GetLocalSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getLocalSettings: GetLocalSettings,
    private val authentication: Authentication
) : BaseViewModel() {
    private var _getAuthenticationResponse = MutableLiveData<Boolean?>()
    val authenticationResponse: LiveData<Boolean?> =
        _getAuthenticationResponse
    fun getUserSettings() = getLocalSettings.execute(this, Unit)

    fun auth(userModel: UserModel)= authentication.execute(this, Authentication.Params(userModel))

}