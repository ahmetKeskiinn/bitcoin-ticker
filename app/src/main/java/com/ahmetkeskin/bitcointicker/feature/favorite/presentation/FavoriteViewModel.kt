package com.ahmetkeskin.bitcointicker.feature.favorite.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.favorite.domain.GetFavorites
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavorites: GetFavorites
) : BaseViewModel() {
    fun getFavorites() = getFavorites.execute(viewModel = this, Unit)
}