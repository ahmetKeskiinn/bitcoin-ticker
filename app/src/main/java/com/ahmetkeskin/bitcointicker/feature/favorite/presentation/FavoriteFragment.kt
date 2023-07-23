package com.ahmetkeskin.bitcointicker.feature.favorite.presentation

import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.common.CurrencyListAdapter
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.databinding.FragmentFavoriteBinding
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem
import com.ahmetkeskin.bitcointicker.feature.home.presentation.CurrencyClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(
    layoutId = R.layout.fragment_favorite
) {
    private var adapter: CurrencyListAdapter? = null

    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[FavoriteViewModel::class.java]
        }
    }

    override fun onResume() {
        super.onResume()
        initRv()
        getAllFavorites()
        initTextWatcher()
    }

    private fun initRv() {
        adapter = CurrencyListAdapter(
            object : CurrencyClickListener {
                override fun isCurrencyClicked(item: CryptoIconItem) {
                    val action =
                        FavoriteFragmentDirections.actionNavigationFavoriteToDetailFragment(
                            item,
                            true
                        )
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }
        )
        binding.favoriteRv.layoutManager = LinearLayoutManager(context)
        binding.favoriteRv.adapter = adapter
    }

    private fun initTextWatcher() {
        binding.searchEditText.doAfterTextChanged {
            it?.let {
                if (it.toString().isNotEmpty()) {
                    getSearchedCurrency(it.toString())
                } else {
                    getAllFavorites()
                }
            }
        }
    }

    private fun getSearchedCurrency(searchQuery: String) {
        viewModel.searchCurrency(searchQuery).observe(
            viewLifecycleOwner,
            Observer {
                adapter?.submitList(convertFavoriteLists(it))
            }
        )
    }

    private fun convertFavoriteLists(list: List<FavoriteModel>?): ArrayList<CryptoIconItem> {
        val favList = arrayListOf<CryptoIconItem>()
        list?.forEach { item ->
            favList.add(
                CryptoIconItem(
                    item.favCoinName,
                    item.url
                )
            )
        }
        return favList
    }

    private fun getAllFavorites() {
        viewModel.getFavorites().observe(
            viewLifecycleOwner,
            Observer {
                adapter?.submitList(convertFavoriteLists(it))
            }
        )
    }
}
