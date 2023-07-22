package com.ahmetkeskin.bitcointicker.feature.history.presentation.pager

data class ChartItem(
    val currency: String,
    val rate: String,
    val dateTime: String
)