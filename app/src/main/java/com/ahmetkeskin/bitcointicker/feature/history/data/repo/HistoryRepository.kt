package com.ahmetkeskin.bitcointicker.feature.history.data.repo

import com.ahmetkeskin.bitcointicker.feature.history.data.request.HistoryRequest
import com.ahmetkeskin.bitcointicker.feature.history.data.response.HistoryResponseItem

interface HistoryRepository {

    suspend fun getHistory(
        historyRequest: HistoryRequest
    ): List<HistoryResponseItem>?
}
