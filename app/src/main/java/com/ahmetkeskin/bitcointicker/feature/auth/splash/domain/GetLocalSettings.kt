package com.ahmetkeskin.bitcointicker.feature.auth.splash.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo.SplashRepository
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetLocalSettings @Inject constructor(
    private val splashRepository: SplashRepository
) : BaseUseCase<MutableLiveData<UserModel>, Unit>() {

    override fun execute(
        viewModel: BaseViewModel,
        input: Unit?
    ): MutableLiveData<UserModel> {
        return MutableLiveData<UserModel>().apply {
            input?.let {
                viewModel.viewModelScope.launch {
                    value =
                        splashRepository.getLocalSettings()
                }
            }
        }
    }
}
