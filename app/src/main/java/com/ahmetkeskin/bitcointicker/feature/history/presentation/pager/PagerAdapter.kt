package com.ahmetkeskin.bitcointicker.feature.history.presentation.pager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerType

class PagerAdapter(
    activity: AppCompatActivity,
    private val itemsCount: Int,
    val type: List<PagerType>,
    private val baseCurrency: String?,
    private val baseCurrencyIcon: String?,
    private val quoteCurrency: String?
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return PagerFragment(type[position], baseCurrency, baseCurrencyIcon, quoteCurrency)
    }
}
