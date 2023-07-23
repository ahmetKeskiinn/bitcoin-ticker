package com.ahmetkeskin.bitcointicker.feature.history.presentation.pager

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.extensions.EMPTY
import com.ahmetkeskin.bitcointicker.databinding.FragmentPagerBinding
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerListingItem
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerType
import com.ahmetkeskin.bitcointicker.feature.history.data.response.HistoryResponseItem
import com.ahmetkeskin.bitcointicker.feature.history.presentation.HistoryViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagerFragment(
    private val type: PagerType,
    private val baseCurrency: String?,
    private val baseCurrencyIcon: String?,
    private val quoteCurrency: String?
) :
    BaseFragment<FragmentPagerBinding, HistoryViewModel>(
        layoutId = R.layout.fragment_pager
    ) {

    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[HistoryViewModel::class.java]
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.historyLiveData.observe(this) {
            it?.let {
                when (type) {
                    PagerType.LISTING -> initHistoryRv(it)
                    PagerType.CHART -> prepareChart(it)
                }
            }
        }
    }

    private fun generateListChartList(historyResponseItems: MutableList<HistoryResponseItem>): ArrayList<Entry> {
        val listingList = arrayListOf<Entry>()
        historyResponseItems.forEachIndexed { index, historyResponseItem ->
            listingList.add(
                Entry(
                    index.toFloat(),
                    historyResponseItem.rate_close?.toFloat() ?: 0F
                )
            )
        }
        return listingList
    }

    private fun prepareChart(historyResponseItems: MutableList<HistoryResponseItem>) {
        binding.lineChart.isVisible = true
        val lineChart = binding.lineChart
        val vl = LineDataSet(generateListChartList(historyResponseItems), EMPTY)

        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.selectedColor
        vl.fillAlpha = R.color.black
        lineChart.xAxis.labelRotationAngle = 0f

        lineChart.data = LineData(vl)
        lineChart.axisRight.isEnabled = false

        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        lineChart.animateX(1000, Easing.EaseInCirc)
        val markerView = context?.let { CustomMarker(it, R.layout.marker_view) }
        lineChart.marker = markerView
    }

    private fun initHistoryRv(historyResponseItems: List<HistoryResponseItem>) {
        val adapter = HistoryAdapter()
        binding.historyRv.layoutManager = LinearLayoutManager(context)
        binding.historyRv.adapter = adapter
        adapter.submitList(generateList(historyResponseItems))
    }

    private fun generateList(historyResponseItems: List<HistoryResponseItem>): ArrayList<PagerListingItem> {
        val listingList = arrayListOf<PagerListingItem>()
        historyResponseItems.forEach {
            listingList.add(
                PagerListingItem(
                    baseCurrency = baseCurrency,
                    baseCurrencyIcon = baseCurrencyIcon,
                    quoteCurrency = quoteCurrency,
                    closedTime = it.time_close,
                    closedRate = it.rate_close
                )
            )
        }
        return listingList
    }
}
