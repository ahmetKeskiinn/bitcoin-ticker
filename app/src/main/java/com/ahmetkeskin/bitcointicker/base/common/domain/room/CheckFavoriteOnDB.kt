package com.ahmetkeskin.bitcointicker.base.common.domain.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.data.repo.FavRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckFavoriteOnDB @Inject constructor(
    private val room: FavRepository
) : BaseUseCase<MutableLiveData<List<FavoriteModel>?>, CheckFavoriteOnDB.Params>() {

    data class Params(
        val favName: String
    )

    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ): MutableLiveData<List<FavoriteModel>?> {
        return MutableLiveData<List<FavoriteModel>?>().apply {
            input?.let {
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    postValue(room.checkFav(name = it.favName))
                }
            }
        }
    }
}
