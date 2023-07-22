package com.ahmetkeskin.bitcointicker.feature.history.data.request

data class HistoryRequest(
    val asset_id_base: String,
    val asset_id_quote: String,
    val period_id: String,
    val time_start: String,
    val time_end: String
)