package com.ahmetkeskin.bitcointicker.feature.history.data.pager

data class PagerListingItem(
    val baseCurrency: String? = null,
    val baseCurrencyIcon: String? = null,
    val quoteCurrency: String? = null,
    val closedTime: String? = null,
    val closedRate: Double? = null
)