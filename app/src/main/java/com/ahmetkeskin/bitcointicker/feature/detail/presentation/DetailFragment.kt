package com.ahmetkeskin.bitcointicker.feature.detail.presentation

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.loadImage
import com.ahmetkeskin.bitcointicker.databinding.FragmentDetailBinding
import com.ahmetkeskin.bitcointicker.feature.detail.data.response.CurrentAndOtherPriceItem
import com.ahmetkeskin.bitcointicker.feature.detail.domain.GetDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    layoutId = R.layout.fragment_detail
) {
    val args: DetailFragmentArgs by navArgs()

    private var adapter: CurrentAndOtherPriceListAdapter? = null

    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[DetailViewModel::class.java]
        }
    }

    override fun onResume() {
        super.onResume()
        initUI()
        initRv()
        initClickListener()
    }

    private fun getCryptoDetail() {
        viewModel?.getDetail(GetDetail.Params(args.currency?.asset_id))
            ?.observe(viewLifecycleOwner, Observer {
                Log.d("TAG", "getCrypto: " + it.rates.toString())
                val rateList = arrayListOf<CurrentAndOtherPriceItem>()
                it.rates?.forEach { item ->
                    rateList.add(
                        CurrentAndOtherPriceItem(
                            args.currency?.asset_id,
                            args.currency?.url,
                            item?.asset_id_quote,
                            item?.rate
                        )
                    )
                }
                adapter?.submitList(rateList)
            })
    }

    private fun initRv() {
        adapter = CurrentAndOtherPriceListAdapter(object : CurrentAndOtherPriceClickListener {
            override fun isCurrentAndOtherPriceClicked(item: CurrentAndOtherPriceItem) {
                val action =
                    DetailFragmentDirections.actionDetailFragmentToHistoryFragment(historyItem = item)
                Navigation.findNavController(binding.root).navigate(action)
            }
        })
        binding.priceForOthersRv.layoutManager = LinearLayoutManager(context)
        binding.priceForOthersRv.adapter = adapter
        getCryptoDetail()
    }

    private fun initUI() {
        binding.currencyImage.loadImage(args.currency?.url)
        binding.currencyName.text = args.currency?.asset_id
        backPressed(R.id.action_detailFragment_to_homeFragment)
    }
    private fun initClickListener() {
        binding.back.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }
    private fun addFavorite() {
        viewModel?.addFavorite()
    }
}