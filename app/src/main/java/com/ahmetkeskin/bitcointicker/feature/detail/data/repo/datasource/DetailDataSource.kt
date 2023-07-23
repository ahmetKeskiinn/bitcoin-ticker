package com.ahmetkeskin.bitcointicker.feature.detail.data.repo.datasource

import com.ahmetkeskin.bitcointicker.feature.detail.data.DetailApiService
import javax.inject.Inject

class DetailDataSource @Inject constructor(
    private val apiService: DetailApiService
) {
    suspend fun getDetail(id: String?) = apiService.getDetail(id)
}
