package com.ahmetkeskin.bitcointicker.base.common.domain.room

import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.data.repo.FavRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFavoriteOnDB @Inject constructor(
    private val room: FavRepository
) : BaseUseCase<Unit, AddFavoriteOnDB.Params>() {

    data class Params(
        val favoriteModel: FavoriteModel
    )

    override fun execute(viewModel: BaseViewModel, input: Params?) {
        input?.let {
            viewModel.viewModelScope.launch(Dispatchers.IO) {
                room.insertFavorite(it.favoriteModel)
            }
        }
    }
}
