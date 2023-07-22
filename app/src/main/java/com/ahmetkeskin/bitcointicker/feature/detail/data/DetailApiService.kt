package com.ahmetkeskin.bitcointicker.feature.detail.data

import com.ahmetkeskin.bitcointicker.BuildConfig
import com.ahmetkeskin.bitcointicker.feature.detail.data.response.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DetailApiService {
    @Headers("X-CoinAPI-Key: ${BuildConfig.API_KEY}")
    @GET("/v1/exchangerate/{asset_id_base}?invert=false")
    suspend fun getDetail(@Path("asset_id_base") id: String?): DetailResponse
}