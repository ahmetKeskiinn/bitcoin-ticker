package com.ahmetkeskin.bitcointicker.feature.detail.data.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("asset_id_base")
    val asset_id_base: String? = null,
    @SerializedName("rates")
    val rates: List<RateItem?>? = null
)
