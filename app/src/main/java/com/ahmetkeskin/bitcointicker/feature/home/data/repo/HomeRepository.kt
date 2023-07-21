package com.ahmetkeskin.bitcointicker.feature.home.data.repo

import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem

interface HomeRepository {
    suspend fun getCryptos(): List<CryptoIconItem>
}