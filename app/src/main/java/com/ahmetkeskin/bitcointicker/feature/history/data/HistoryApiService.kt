package com.ahmetkeskin.bitcointicker.feature.history.data

import com.ahmetkeskin.bitcointicker.BuildConfig
import com.ahmetkeskin.bitcointicker.feature.history.data.response.HistoryResponseItem
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface HistoryApiService {
    @Headers("X-CoinAPI-Key: ${BuildConfig.API_KEY}")
    @GET("v1/exchangerate/{asset_id_base}/{asset_id_quote}/history")
    suspend fun getHistory(
        @Path("asset_id_base") asset_id_base: String?,
        @Path("asset_id_quote") asset_id_quote: String?,
        @Query("period_id") period_id: String?,
        @Query("time_start") time_start: String,
        @Query("time_end") time_end: String
    ): List<HistoryResponseItem>?
}