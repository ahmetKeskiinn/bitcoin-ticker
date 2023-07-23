package com.ahmetkeskin.bitcointicker.feature.detail.presentation

import com.ahmetkeskin.bitcointicker.feature.detail.data.response.CurrentAndOtherPriceItem

interface CurrentAndOtherPriceClickListener {
    fun isCurrentAndOtherPriceClicked(item: CurrentAndOtherPriceItem)
}
