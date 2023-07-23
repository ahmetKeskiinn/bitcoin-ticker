package com.ahmetkeskin.bitcointicker.feature.detail.data.repo

import com.ahmetkeskin.bitcointicker.feature.detail.data.response.DetailResponse

interface DetailRepository {
    suspend fun getDetail(id: String?): DetailResponse

    suspend fun addFavorite()
}
