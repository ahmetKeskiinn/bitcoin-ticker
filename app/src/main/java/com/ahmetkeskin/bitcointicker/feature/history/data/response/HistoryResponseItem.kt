package com.ahmetkeskin.bitcointicker.feature.history.data.response

import com.google.gson.annotations.SerializedName

data class HistoryResponseItem(
    @SerializedName("time_period_start")
    val time_period_start: String? = null,
    @SerializedName("time_period_end")
    val time_period_end: String? = null,
    @SerializedName("time_open")
    val time_open: String? = null,
    @SerializedName("time_close")
    val time_close: String? = null,
    @SerializedName("rate_open")
    val rate_open: Double? = null,
    @SerializedName("rate_high")
    val rate_high: Double? = null,
    @SerializedName("rate_low")
    val rate_low: Double? = null,
    @SerializedName("rate_close")
    val rate_close: Double? = null
)