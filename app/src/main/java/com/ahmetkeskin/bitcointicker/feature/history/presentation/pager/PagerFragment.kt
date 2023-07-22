package com.ahmetkeskin.bitcointicker.feature.history.presentation.pager

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.EMPTY
import com.ahmetkeskin.bitcointicker.databinding.FragmentPagerBinding
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerListingItem
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerType
import com.ahmetkeskin.bitcointicker.feature.history.data.response.HistoryResponseItem
import com.ahmetkeskin.bitcointicker.feature.history.presentation.HistoryViewModel
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
    companion object {
        private const val MAPPING = "{ x: 'x', value: 'value' }"
    }

    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[HistoryViewModel::class.java]
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.historyLiveData.observe(this) {
            it?.let {
                Log.d("TAG", "observeViewModel:-> " + it.size)
                when (type) {
                    PagerType.LISTING -> initHistoryRv(it)
                    PagerType.CHART -> prepareChart(it)
                }
            }
        }
    }

    private fun prepareChart(historyResponseItems: List<HistoryResponseItem>) {
        val chartView = binding.newChart
        chartView.adapter =
            ChartAdapter(quoteCurrency ?: EMPTY, generateList(historyResponseItems))

        chartView.addOnPointSelectedObserver { data ->
            val currentCompare = data as ChartItem
            binding.selectedPointTextview.text = "1 $baseCurrency = ${currentCompare.rate}"
            val newDate = currentCompare.dateTime.split(".")[0].split("T")
            binding.selectedPointDate.text = "${newDate[1]} ${newDate[0]}"
        }
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