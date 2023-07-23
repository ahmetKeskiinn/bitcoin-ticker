package com.ahmetkeskin.bitcointicker.feature.detail.data.repo

import com.ahmetkeskin.bitcointicker.feature.detail.data.repo.datasource.DetailDataSource
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dataSource: DetailDataSource
) : DetailRepository {

    override suspend fun getDetail(id: String?) = dataSource.getDetail(id)

    override suspend fun addFavorite() {
    }
}
