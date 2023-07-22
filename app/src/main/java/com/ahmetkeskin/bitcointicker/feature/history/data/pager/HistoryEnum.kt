package com.ahmetkeskin.bitcointicker.feature.history.data.pager

enum class HistoryEnum(val day: Int) {
    ONE_DAY(1),
    FIVE_DAY(5),
    ONE_MONTH(30),
    SIX_MONTHS(180),
    ONE_YEAR(365),
    FIVE_YEAR(1825),
}