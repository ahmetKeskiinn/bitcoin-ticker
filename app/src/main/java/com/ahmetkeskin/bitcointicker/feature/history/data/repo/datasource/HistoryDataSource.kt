package com.ahmetkeskin.bitcointicker.feature.history.data.repo.datasource

import com.ahmetkeskin.bitcointicker.feature.history.data.HistoryApiService
import com.ahmetkeskin.bitcointicker.feature.history.data.request.HistoryRequest
import javax.inject.Inject

class HistoryDataSource @Inject constructor(
    private val apiService: HistoryApiService
) {
    suspend fun getHistory(historyRequest: HistoryRequest) =
        apiService.getHistory(
            asset_id_base = historyRequest.asset_id_base,
            asset_id_quote = historyRequest.asset_id_quote,
            period_id = historyRequest.period_id,
            time_start = historyRequest.time_start,
            time_end = historyRequest.time_end
        )
}