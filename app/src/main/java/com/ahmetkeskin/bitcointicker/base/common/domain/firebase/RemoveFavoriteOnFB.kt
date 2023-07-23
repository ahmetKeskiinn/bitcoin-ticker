package com.ahmetkeskin.bitcointicker.base.common.domain.firebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.data.repo.FavRepository
import com.ahmetkeskin.bitcointicker.base.common.domain.room.RemoveFavoriteOnDB
import com.ahmetkeskin.bitcointicker.base.extensions.EMPTY
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RemoveFavoriteOnFB @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val removeFavoriteOnDB: RemoveFavoriteOnDB,
    private val room: FavRepository,
) : BaseUseCase<MutableLiveData<Boolean?>, RemoveFavoriteOnFB.Params>() {

    data class Params(
        val favoriteModel: FavoriteModel
    )

    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ): MutableLiveData<Boolean?> {
        return MutableLiveData<Boolean?>().apply {
            input?.let { params ->
                viewModel.viewModelScope.launch(Dispatchers.IO) {
                    val checkList = room.checkFav(params.favoriteModel.favCoinName)
                    if (checkList.isNotEmpty()) {
                        firebaseAuth.currentUser?.let { user ->
                            fireStore.collection(user.uid).document(checkList[0].id ?: EMPTY)
                                .delete()
                                .addOnSuccessListener {
                                    removeFavoriteOnDB.execute(
                                        viewModel,
                                        RemoveFavoriteOnDB.Params(
                                            FavoriteModel(
                                                id = checkList[0].id,
                                                favCoinName = params.favoriteModel.favCoinName,
                                                favCoinRate = params.favoriteModel.favCoinRate,
                                                url = params.favoriteModel.url
                                            )
                                        )
                                    )
                                    value = true
                                }.addOnFailureListener {
                                    value = false
                                }
                        }
                    }
                }
            }
        }
    }
}
