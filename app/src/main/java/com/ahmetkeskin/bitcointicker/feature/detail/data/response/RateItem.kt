package com.ahmetkeskin.bitcointicker.feature.detail.data.response

import com.google.gson.annotations.SerializedName

data class RateItem(
    @SerializedName("time")
    val time: String? = null,
    @SerializedName("asset_id_quote")
    val asset_id_quote: String? = null,
    @SerializedName("rate")
    val rate: Double? = null
)