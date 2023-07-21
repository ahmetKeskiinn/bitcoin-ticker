package com.ahmetkeskin.bitcointicker.feature.home.presentation

import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem

interface CurrencyClickListener {
    fun isCurrencyClicked(item: CryptoIconItem)
}