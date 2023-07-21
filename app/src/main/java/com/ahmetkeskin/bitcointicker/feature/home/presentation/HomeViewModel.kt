package com.ahmetkeskin.bitcointicker.feature.home.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.home.domain.GetCryptos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weather: GetCryptos,
) : BaseViewModel() {
    fun getWeather() = weather.execute(this, Unit)
}