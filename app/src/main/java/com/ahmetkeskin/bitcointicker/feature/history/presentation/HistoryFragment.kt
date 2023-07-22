package com.ahmetkeskin.bitcointicker.feature.history.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.EMPTY
import com.ahmetkeskin.bitcointicker.base.getCurrentDate
import com.ahmetkeskin.bitcointicker.base.getMinusForCurrentDate
import com.ahmetkeskin.bitcointicker.databinding.FragmentHistoryBinding
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.HistoryEnum
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PagerType
import com.ahmetkeskin.bitcointicker.feature.history.data.pager.PeriodEnum
import com.ahmetkeskin.bitcointicker.feature.history.domain.GetHistory
import com.ahmetkeskin.bitcointicker.feature.history.presentation.pager.PagerAdapter
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : BaseFragment<FragmentHistoryBinding, HistoryViewModel>(
    layoutId = R.layout.fragment_history
) {
    private val args: HistoryFragmentArgs by navArgs()

    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[HistoryViewModel::class.java]
        }
    }

    private var currentOffset: Int? = null
    private var currentPeriod = "1MIN"

    override fun onResume() {
        super.onResume()
        initUI()
        getHistory()
        initTabLayout()
        initHistoryPager()
        initClickListener()
    }

    private fun initUI() {
        binding.homeCurrency.text = args.historyItem?.currentItem ?: EMPTY
        binding.awayCurrency.text = args.historyItem?.asset_id_quote ?: EMPTY
    }

    private fun initClickListener() {
        binding.offsetSpinner.setOnClickListener {
            showOffsetDialog()
        }
        binding.periodSpinner.setOnClickListener {
            showPeriodDialog()
        }
        binding.back.setOnClickListener {
            val action = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(
                currency = CryptoIconItem(
                    args.historyItem?.currentItem,
                    args.historyItem?.currentItemUrl
                )
            )
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun getHistory() {
        viewModel.getHistory(
            GetHistory.Params(
                asset_id_base = args.historyItem?.currentItem ?: EMPTY,
                asset_id_quote = args.historyItem?.asset_id_quote ?: EMPTY,
                period_id = currentPeriod,
                time_start = getMinusForCurrentDate(currentOffset),
                time_end = getCurrentDate()
            )
        )
    }

    private fun showOffsetDialog() {
        MaterialDialog(requireContext()).show {
            listItems(items = getOffsetTimes()) { _, index, title ->
                binding.offsetSpinner.text = title
                currentOffset = getOffsetValue(index)
                getHistory()
            }
        }
    }

    private fun showPeriodDialog() {
        MaterialDialog(requireContext()).show {
            listItems(items = getPeriodTimes()) { _, index, title ->
                binding.periodSpinner.text = title
                currentPeriod = getPeriodValue(index)
                getHistory()
            }
        }
    }

    private fun getPeriodValue(index: Int) = PeriodEnum.values()[index].period
    private fun getOffsetValue(index: Int) = HistoryEnum.values()[index].day
    private fun getOffsetTimes() = arrayListOf(
        getString(R.string.one_day),
        getString(R.string.five_day),
        getString(R.string.one_month),
        getString(R.string.six_months),
        getString(R.string.one_year),
        getString(R.string.five_year)
    )

    private fun getPeriodTimes() =
        arrayListOf(
            getString(R.string.period_one_sec),
            getString(R.string.period_two_sec),
            getString(R.string.period_three_sec),
            getString(R.string.period_four_sec),
            getString(R.string.period_five_sec),
            getString(R.string.period_six_sec),
            getString(R.string.period_ten_sec),
            getString(R.string.period_twenty_sec),
            getString(R.string.period_thirty_sec),
            getString(R.string.period_one_min),
            getString(R.string.period_two_min),
            getString(R.string.period_three_min),
            getString(R.string.period_four_min),
            getString(R.string.period_five_min),
            getString(R.string.period_six_min),
            getString(R.string.period_ten_min),
            getString(R.string.period_fifteen_min),
            getString(R.string.period_twenty_min),
            getString(R.string.period_thirty_min),
            getString(R.string.period_one_hour),
            getString(R.string.period_two_hour),
            getString(R.string.period_three_hour),
            getString(R.string.period_four_hour),
            getString(R.string.period_six_hour),
            getString(R.string.period_eight_hour),
            getString(R.string.period_twelve_hour),
            getString(R.string.period_one_day),
            getString(R.string.period_two_day),
            getString(R.string.period_three_day),
            getString(R.string.five_day),
            getString(R.string.period_seven_day),
            getString(R.string.period_ten_day)
        )

    private fun initHistoryPager() {
        binding.historyShowPager.run {
            if (adapter == null) {
                val pagerAdapter = PagerAdapter(
                    activity = activity as AppCompatActivity,
                    2,
                    type = listOf(
                        PagerType.LISTING,
                        PagerType.CHART
                    ),
                    args.historyItem?.currentItem,
                    args.historyItem?.currentItemUrl,
                    args.historyItem?.asset_id_quote
                )
                adapter = pagerAdapter
            }
            isUserInputEnabled = false
        }
    }

    private fun initTabLayout() {
        binding.screenTypeTabs.run {
            if (tabCount == 0) {
                background = activity?.getDrawable(R.drawable.bg_currency_item)
                activity?.let {
                    this.setTabTextColors(
                        it.getColor(R.color.white),
                        it.getColor(R.color.selectedColor)
                    )
                }
                addNewTabsOnTabLayout()
                addPositionChangeListenerOnTabLayout()
            }

        }
    }

    private fun addNewTabsOnTabLayout() {
        binding.screenTypeTabs.run {
            val listingTab = newTab()
            listingTab.text = getString(R.string.listing_tab)
            addTab(listingTab)
            val chartTab = newTab()
            chartTab.text = getString(R.string.chart_tab)
            addTab(chartTab)
        }

    }

    private fun addPositionChangeListenerOnTabLayout() {
        binding.screenTypeTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        binding.historyShowPager.setCurrentItem(0, true)
                    }
                    1 -> {
                        binding.historyShowPager.setCurrentItem(1, true)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit

            override fun onTabReselected(tab: TabLayout.Tab?) = Unit

        })
    }

    private fun initSpinner() {

    }
}