package com.ahmetkeskin.bitcointicker.feature.auth.splash.presentation

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ahmetkeskin.bitcointicker.R
import com.ahmetkeskin.bitcointicker.base.BaseFragment
import com.ahmetkeskin.bitcointicker.components.TickerButtonClicked
import com.ahmetkeskin.bitcointicker.components.buttonattributes.ButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.Gravity
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonAttributes
import com.ahmetkeskin.bitcointicker.components.buttonattributes.TickerButtonTextAttributes
import com.ahmetkeskin.bitcointicker.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(
    layoutId = R.layout.fragment_splash
) {
    override fun onInitDataBinding() {
        activity?.let {
            viewModel = ViewModelProvider(it)[SplashViewModel::class.java]
        }
    }
}