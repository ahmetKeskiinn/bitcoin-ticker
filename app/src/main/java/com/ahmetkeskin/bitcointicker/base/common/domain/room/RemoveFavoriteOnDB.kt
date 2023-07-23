package com.ahmetkeskin.bitcointicker.base.common.domain.room

import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteDao
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class RemoveFavoriteOnDB @Inject constructor(
    private val room: FavoriteDao
) : BaseUseCase<Unit, RemoveFavoriteOnDB.Params>() {

    data class Params(
        val favoriteModel: FavoriteModel
    )

    override fun execute(viewModel: BaseViewModel, input: Params?) {
        input?.let {
            viewModel.viewModelScope.launch {
                room.deleteFavorite(it.favoriteModel)
            }
        }
    }
}
