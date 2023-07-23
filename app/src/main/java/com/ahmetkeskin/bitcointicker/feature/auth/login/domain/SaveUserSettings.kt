package com.ahmetkeskin.bitcointicker.feature.auth.login.domain

import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo.LoginRepository
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class SaveUserSettings @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<Unit, SaveUserSettings.Params>() {
    data class Params(
        val userModel: UserModel
    )

    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ) {
        viewModel.viewModelScope.launch {
            input?.userModel?.let { loginRepository.saveUserSettings(it) }
        }
    }
}