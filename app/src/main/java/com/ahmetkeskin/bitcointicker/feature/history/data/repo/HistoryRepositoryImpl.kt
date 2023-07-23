package com.ahmetkeskin.bitcointicker.feature.history.data.repo

import com.ahmetkeskin.bitcointicker.feature.history.data.repo.datasource.HistoryDataSource
import com.ahmetkeskin.bitcointicker.feature.history.data.request.HistoryRequest
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val dataSource: HistoryDataSource
) : HistoryRepository {

    override suspend fun getHistory(
        historyRequest: HistoryRequest
    ) = dataSource.getHistory(historyRequest)
}
