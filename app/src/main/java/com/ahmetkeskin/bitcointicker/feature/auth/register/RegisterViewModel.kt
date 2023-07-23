package com.ahmetkeskin.bitcointicker.feature.auth.register

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.home.domain.GetCryptos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getCryptos: GetCryptos
) : BaseViewModel() {
    fun getCrypto() = getCryptos.execute(this, Unit)
}