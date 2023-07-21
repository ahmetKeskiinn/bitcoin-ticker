package com.ahmetkeskin.bitcointicker.feature.home.data.datasource

import com.ahmetkeskin.bitcointicker.feature.home.data.HomeApiService
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val apiService: HomeApiService
) {
    suspend fun getCryptos() = apiService.getCryptos()
}