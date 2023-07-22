package com.ahmetkeskin.bitcointicker.feature.history.domain

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.history.data.repo.HistoryRepository
import com.ahmetkeskin.bitcointicker.feature.history.data.request.HistoryRequest
import com.ahmetkeskin.bitcointicker.feature.history.data.response.HistoryResponseItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetHistory @Inject constructor(
    private val detailRepository: HistoryRepository
) : BaseUseCase<Unit, GetHistory.Params>() {
    data class Params(
        val asset_id_base: String,
        val asset_id_quote: String,
        val period_id: String,
        val time_start: String,
        val time_end: String
    )
    var _getHistoryResponse = MutableLiveData<MutableList<HistoryResponseItem>?>()
    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ) {
        _getHistoryResponse.apply {
            input?.let {
                viewModel.viewModelScope.launch {
                    value =
                        detailRepository.getHistory(
                            HistoryRequest(
                                asset_id_base = it.asset_id_base,
                                asset_id_quote = it.asset_id_quote,
                                period_id = it.period_id,
                                time_start = it.time_start,
                                time_end = it.time_end
                            )
                        )?.toMutableList()
                    Log.d("TAG", "execute: " + value)
                }
            }
        }
    }
}