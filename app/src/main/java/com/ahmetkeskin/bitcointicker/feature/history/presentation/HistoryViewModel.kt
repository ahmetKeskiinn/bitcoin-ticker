package com.ahmetkeskin.bitcointicker.feature.history.presentation

import androidx.lifecycle.LiveData
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.history.data.response.HistoryResponseItem
import com.ahmetkeskin.bitcointicker.feature.history.domain.GetHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistory: GetHistory
) : BaseViewModel() {
    private var _getHistoryResponse = getHistory._getHistoryResponse
    val historyLiveData: LiveData<MutableList<HistoryResponseItem>?> =
        _getHistoryResponse

    fun getHistory(historyParams: GetHistory.Params) {
        getHistory.execute(this, historyParams)
    }
}
