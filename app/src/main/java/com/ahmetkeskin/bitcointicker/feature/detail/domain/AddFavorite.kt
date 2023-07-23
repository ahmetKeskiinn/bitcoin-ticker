package com.ahmetkeskin.bitcointicker.feature.detail.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.detail.data.repo.DetailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFavorite @Inject constructor(
    private val detailRepository: DetailRepository
) : BaseUseCase<MutableLiveData<Unit>, Unit>() {

    override fun execute(viewModel: BaseViewModel, input: Unit?): MutableLiveData<Unit> {
        return MutableLiveData<Unit>().apply {
            viewModel.viewModelScope.launch {
                value =
                    detailRepository.addFavorite()
            }
        }
    }
}
