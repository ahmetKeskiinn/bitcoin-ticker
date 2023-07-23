package com.ahmetkeskin.bitcointicker.feature.favorite.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.favorite.domain.GetFavorites
import com.ahmetkeskin.bitcointicker.feature.favorite.domain.GetSearchedFavorite
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavorites: GetFavorites,
    private val getSearchedFavorite: GetSearchedFavorite
) : BaseViewModel() {
    fun getFavorites() = getFavorites.execute(viewModel = this, Unit)
    fun searchCurrency(query: String) =
        getSearchedFavorite.execute(viewModel = this, GetSearchedFavorite.Params(query))
}
