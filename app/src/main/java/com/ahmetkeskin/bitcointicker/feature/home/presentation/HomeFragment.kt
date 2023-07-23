package com.ahmetkeskin.bitcointicker.feature.home.presentation

import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.base.common.CurrencyListAdapter
import com.ahmetkeskin.bitcointicker.databinding.FragmentHomeBinding
import com.ahmetkeskin.bitcointicker.feature.home.data.response.CryptoIconItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {
    private var adapter: CurrencyListAdapter? = null
    var currencyList: List<CryptoIconItem>? = null
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]
        }
    }

    override fun onResume() {
        super.onResume()
        initRv()
        initUI()
    }

    private fun initUI() {
        backPressed()
        initTextWatcher()
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
        val newCurrencyList = arrayListOf<CryptoIconItem>()
        currencyList?.forEach {
            if (it.asset_id?.lowercase()?.contains(searchQuery.lowercase()) == true) {
                newCurrencyList.add(it)
            } else {
                Log.d("TAG", "searchWithQuery: " + it.asset_id)
            }
        }
        Log.d("TAG", "searchWithQuery: " + newCurrencyList)
        adapter?.submitList(newCurrencyList)
    }
    private fun getCrypto() {
        showProgress()
        // print(0/0)
        viewModel.getCrypto()?.observe(
            viewLifecycleOwner,
            Observer { list ->
                currencyList = list
                adapter?.submitList(list)
                hideProgress()
            }
        )
    }

    private fun initRv() {
        adapter = CurrencyListAdapter(object : CurrencyClickListener {
            override fun isCurrencyClicked(item: CryptoIconItem) {
                val action =
                    HomeFragmentDirections.actionNavigationHomeToDetailFragment(item, false)
                Navigation.findNavController(binding.root).navigate(action)
            }
        })
        binding.currencyRv.layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        binding.currencyRv.adapter = adapter
        getCrypto()
    }
}
