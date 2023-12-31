package com.ahmetkeskin.bitcointicker.feature.home.data

import com.ahmetkeskin.bitcointicker.BuildConfig
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface HomeApiService {
    @Headers("X-CoinAPI-Key: ${BuildConfig.API_KEY}")
    @GET("/v1/assets/icons/50")
    suspend fun getCryptos(): List<CryptoIconItem>
}
