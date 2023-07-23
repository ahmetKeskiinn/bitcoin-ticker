package com.ahmetkeskin.bitcointicker.feature.auth.register.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.auth.register.domain.SetRegisterOnFB
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val setRegisterOnFB: SetRegisterOnFB
) : BaseViewModel() {
    fun register(userModel: UserModel) = setRegisterOnFB.execute(this, SetRegisterOnFB.Params(userModel))
}
