package com.ahmetkeskin.bitcointicker.feature.home.presentation

import androidx.lifecycle.ViewModelProvider
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    layoutId = R.layout.fragment_home
) {
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[HomeViewModel::class.java]
        }
    }

    override fun onResume() {
        super.onResume()
        getCrypto()
    }
    private fun getCrypto() {
        viewModel.getWeather()
    }
}