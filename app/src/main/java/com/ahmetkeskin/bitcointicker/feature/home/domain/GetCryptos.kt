package com.ahmetkeskin.bitcointicker.feature.home.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.home.data.repo.HomeRepository
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetCryptos @Inject constructor(
    private val detailRepository: HomeRepository
) : BaseUseCase<MutableLiveData<CryptoResponse>, Unit>() {

    override fun execute(viewModel: BaseViewModel, input: Unit?): MutableLiveData<CryptoResponse>? {
        return MutableLiveData<CryptoResponse>().apply {
            viewModel.viewModelScope.launch {
                value =
                    detailRepository.getCryptos()
            }
        }
    }
}