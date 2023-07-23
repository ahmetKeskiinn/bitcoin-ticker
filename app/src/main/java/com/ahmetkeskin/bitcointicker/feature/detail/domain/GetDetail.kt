package com.ahmetkeskin.bitcointicker.feature.detail.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.detail.data.repo.DetailRepository
import com.ahmetkeskin.bitcointicker.feature.detail.data.response.DetailResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetDetail @Inject constructor(
    private val detailRepository: DetailRepository
) : BaseUseCase<MutableLiveData<DetailResponse>, GetDetail.Params>() {
    data class Params(
        val id: String? = null
    )
    override fun execute(viewModel: BaseViewModel, input: Params?): MutableLiveData<DetailResponse>? {
        return MutableLiveData<DetailResponse>().apply {
            input?.let {
                viewModel.viewModelScope.launch {
                    value =
                        detailRepository.getDetail(it.id)
                }
            }
        }
    }
}
