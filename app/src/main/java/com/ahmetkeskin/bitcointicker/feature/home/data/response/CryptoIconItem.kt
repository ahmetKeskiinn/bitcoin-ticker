package com.ahmetkeskin.bitcointicker.feature.home.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CryptoIconItem(
    @SerializedName("asset_id")
    val asset_id: String? = null,
    @SerializedName("url")
    val url: String? = null
): Serializable