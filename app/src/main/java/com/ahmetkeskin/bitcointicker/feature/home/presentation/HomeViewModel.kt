package com.ahmetkeskin.bitcointicker.feature.home.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.MergeFavoriteFBAndDB
import com.ahmetkeskin.bitcointicker.feature.home.domain.GetCryptos
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCryptos: GetCryptos,
    private val mergeFavoriteFBAndDB: MergeFavoriteFBAndDB
) : BaseViewModel() {
    fun getCrypto() = getCryptos.execute(this, Unit)

    init {
        mergeFavoriteFBAndDB.execute(
            this,
            Unit
        )
    }
}
