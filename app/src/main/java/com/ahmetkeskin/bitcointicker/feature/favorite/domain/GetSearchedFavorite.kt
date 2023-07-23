package com.ahmetkeskin.bitcointicker.feature.favorite.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.data.repo.FavRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetSearchedFavorite @Inject constructor(
    private val room: FavRepository
) : BaseUseCase<MutableLiveData<List<FavoriteModel>?>, GetSearchedFavorite.Params>() {
    data class Params(
        val query: String
    )

    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ): MutableLiveData<List<FavoriteModel>?> {
        return MutableLiveData<List<FavoriteModel>?>().apply {
            input?.let {
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    postValue(room.searchFav(input.query))
                }
            }
        }
    }
}
