package com.ahmetkeskin.bitcointicker.feature.detail.presentation

import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.EMPTY
import com.ahmetkeskin.bitcointicker.base.USD
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.AddFavoriteOnFB
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.RemoveFavoriteOnFB
import com.ahmetkeskin.bitcointicker.base.common.domain.room.CheckFavoriteOnDB
import com.ahmetkeskin.bitcointicker.base.loadImage
import com.ahmetkeskin.bitcointicker.databinding.FragmentDetailBinding
import com.ahmetkeskin.bitcointicker.feature.detail.data.response.CurrentAndOtherPriceItem
import com.ahmetkeskin.bitcointicker.feature.detail.domain.GetDetail
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    layoutId = R.layout.fragment_detail
) {
    val args: DetailFragmentArgs by navArgs()

    private var adapter: CurrentAndOtherPriceListAdapter? = null
    var currencyList: List<CurrentAndOtherPriceItem>? = null
    private var currentCurrencyWithUSD = EMPTY
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[DetailViewModel::class.java]
        }
    }

    override fun onResume() {
        super.onResume()
        checkCurrencyInDb()
        initUI()
        initRv()
        initClickListener()
        observeViewModel()
    }

    private fun getCryptoDetail() {
        showProgress()
        viewModel.getDetail(GetDetail.Params(args.currency?.asset_id))
            ?.observe(viewLifecycleOwner, Observer {
                val rateList = arrayListOf<CurrentAndOtherPriceItem>()
                it.rates?.forEach { item ->
                    if (item?.asset_id_quote == USD) {
                        currentCurrencyWithUSD = item.rate.toString()
                        binding.currencyName.text =
                            binding.currencyName.text.toString() + " " + item.rate.toString() + " " + USD
                    }
                    rateList.add(
                        CurrentAndOtherPriceItem(
                            args.currency?.asset_id,
                            args.currency?.url,
                            item?.asset_id_quote,
                            item?.rate
                        )
                    )
                }
                currencyList = rateList
                adapter?.submitList(rateList)
                hideProgress()
            })
    }
    private fun initTextWatcher() {
        binding.searchEditText.text.clear()
        binding.searchEditText.doAfterTextChanged {
            it?.let {
                if (it.trim().isNotEmpty()) {
                    searchWithQuery(it.toString())
                } else {
                    adapter?.submitList(currencyList)
                }
            }
        }
    }
    private fun searchWithQuery(searchQuery: String) {
        val newCurrencyList = arrayListOf<CurrentAndOtherPriceItem>()
        currencyList?.forEach {
            if (it.asset_id_quote?.lowercase()?.contains(searchQuery.lowercase()) == true) {
                newCurrencyList.add(it)
            }
        }
        adapter?.submitList(newCurrencyList)
    }
    private fun initRv() {
        adapter = CurrentAndOtherPriceListAdapter(object : CurrentAndOtherPriceClickListener {
            override fun isCurrentAndOtherPriceClicked(item: CurrentAndOtherPriceItem) {
                val action =
                    DetailFragmentDirections.actionDetailFragmentToHistoryFragment(
                        historyItem = item,
                        isFromFavorite = args.fromFavorite
                    )
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
        when (args.fromFavorite) {
            true -> backPressed(R.id.action_detailFragment_to_navigation_favorite)
            false -> backPressed(R.id.action_detailFragment_to_homeFragment)
        }
        initTextWatcher()
    }

    private fun initClickListener() {
        binding.back.setOnClickListener {
            when(args.fromFavorite) {
                true -> Navigation.findNavController(binding.root)
                    .navigate(R.id.action_detailFragment_to_navigation_favorite)
                false -> Navigation.findNavController(binding.root)
                    .navigate(R.id.action_detailFragment_to_homeFragment)
            }
        }
        binding.followIcon.setOnClickListener {
            manageFavorite()
        }
    }

    private fun observeViewModel() {
        viewModel.isFollowing.observe(viewLifecycleOwner, Observer {
            if (it) {
                changeStarIconBg(R.drawable.ic_followed)
            } else {
                changeStarIconBg(R.drawable.ic_unfollowed)
            }
        })
    }

    private fun checkCurrencyInDb() {
        viewModel.checkFollowing(
            CheckFavoriteOnDB.Params(
                args.currency?.asset_id ?: EMPTY
            )
        ).observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.setFollowingState(it.isEmpty().not())
            }
        })
    }

    private fun manageFavorite() {
        showProgress()
        if (viewModel.isFollowing.value == false) {
            addFavorite()
        } else {
            removeFavorite()
        }
    }

    private fun addFavorite() {
        viewModel.addFavorite(
            AddFavoriteOnFB.Params(
                FavoriteModel(
                    favCoinName = args.currency?.asset_id ?: EMPTY,
                    favCoinRate = currentCurrencyWithUSD,
                    url = args.currency?.url ?: EMPTY
                )
            )
        ).observe(viewLifecycleOwner, Observer {
            viewModel.setFollowingState(it ?: false)
            hideProgress()
        })
    }

    private fun removeFavorite() {
        viewModel.removeFavorite(
            RemoveFavoriteOnFB.Params(
                FavoriteModel(
                    favCoinName = args.currency?.asset_id ?: EMPTY,
                    favCoinRate = currentCurrencyWithUSD,
                    url = args.currency?.url ?: EMPTY
                )
            )
        ).observe(viewLifecycleOwner, Observer {
            hideProgress()
            viewModel.setFollowingState(it?.not() ?: true)
        })
    }

    private fun changeStarIconBg(image: Int) = binding.followIcon.setImageResource(image)
}