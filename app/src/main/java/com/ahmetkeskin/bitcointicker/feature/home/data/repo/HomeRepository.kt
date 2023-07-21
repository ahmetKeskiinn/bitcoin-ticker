package com.ahmetkeskin.bitcointicker.feature.home.data.repo

import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoResponse

interface HomeRepository {
    suspend fun getCryptos(): CryptoResponse
}