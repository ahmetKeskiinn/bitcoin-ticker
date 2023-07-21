package com.ahmetkeskin.bitcointicker.feature.home.data.repo

import com.ahmetkeskin.bitcointicker.feature.home.data.datasource.HomeDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getCryptos() = dataSource.getCryptos()
}