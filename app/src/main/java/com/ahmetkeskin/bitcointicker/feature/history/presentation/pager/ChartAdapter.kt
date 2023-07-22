package com.ahmetkeskin.bitcointicker.feature.history.presentation.pager

import com.ahmetkeskin.bitcointicker.base.EMPTY
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerListingItem
import github.com.st235.lib_chartio.ChartioAdaper

class ChartAdapter(
    private val currency: String,
    private val currencyList: List<PagerListingItem>
) : ChartioAdaper() {

    private var size = currencyList.size

    private var values = FloatArray(size)

    init {
        initValues()
    }

    private fun initValues() {
        values = FloatArray(size)
        currencyList.forEachIndexed { index, pagerListingItem ->
            pagerListingItem.closedRate?.let {
                values[index] = it.toFloat()
            }
        }
    }

    override fun getSize(): Int = size

    override fun getY(index: Int): Float = values[index]

    override fun getData(index: Int): Any = ChartItem(
        currency = currency,
        rate = values[index].toString(),
        dateTime = currencyList[index].closedTime ?: EMPTY
    )
}