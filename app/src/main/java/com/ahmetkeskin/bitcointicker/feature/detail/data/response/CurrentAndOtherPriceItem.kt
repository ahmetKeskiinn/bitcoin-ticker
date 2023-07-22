package com.ahmetkeskin.bitcointicker.feature.detail.data.response

data class CurrentAndOtherPriceItem (
    val currentItem: String? = null,
    val currentItemUrl: String? = null,
    val asset_id_quote: String? = null,
    val rate: Double? = null
)