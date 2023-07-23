package com.ahmetkeskin.bitcointicker.feature.home.presentation

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
    private fun initUI(){
        backPressed()
    }
    private fun getCrypto() {
        showProgress()
        viewModel.getCrypto()?.observe(viewLifecycleOwner, Observer { list ->
            adapter?.submitList(list)
            hideProgress()
        })
    }
    private fun initRv() {
        adapter = CurrencyListAdapter(object : CurrencyClickListener {
            override fun isCurrencyClicked(item: CryptoIconItem) {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailFragment(item, false)
                Navigation.findNavController(binding.root).navigate(action)
            }
        })
        binding.currencyRv.layoutManager = GridLayoutManager (context,
            2,
            GridLayoutManager.VERTICAL,
            false)
        binding.currencyRv.adapter = adapter
        getCrypto()
    }
}