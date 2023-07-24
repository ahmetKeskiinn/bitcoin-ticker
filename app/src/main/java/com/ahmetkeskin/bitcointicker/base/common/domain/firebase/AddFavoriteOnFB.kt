package com.ahmetkeskin.bitcointicker.base.common.domain.firebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.domain.room.AddFavoriteOnDB
import com.ahmetkeskin.bitcointicker.base.extensions.FAVORITE_CURRENCY
import com.ahmetkeskin.bitcointicker.base.extensions.FAVORITE_CURRENCY_RATE
import com.ahmetkeskin.bitcointicker.base.extensions.URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddFavoriteOnFB @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val addFavoriteOnDB: AddFavoriteOnDB
) : BaseUseCase<MutableLiveData<Boolean?>, AddFavoriteOnFB.Params>() {

    data class Params(
        val favoriteModel: FavoriteModel
    )

    override fun execute(
        viewModel: BaseViewModel,
        input: Params?
    ): MutableLiveData<Boolean?> {
        return MutableLiveData<Boolean?>().apply {
            input?.let { params ->
                viewModel.viewModelScope.launch {
                    firebaseAuth.currentUser?.let { user ->
                        fireStore.collection(user.uid).add(
                            hashMapOf<String, Any>(
                                FAVORITE_CURRENCY to params.favoriteModel.favCoinName,
                                FAVORITE_CURRENCY_RATE to params.favoriteModel.favCoinRate,
                                URL to params.favoriteModel.url
                            )
                        ).addOnSuccessListener { success ->
                            addToDB(viewModel, success.id, input)
                            value = true
                        }.addOnFailureListener {
                            value = false
                        }
                    }
                }
            }
        }
    }

    private fun addToDB(viewModel: BaseViewModel, id: String, input: Params) {
        addFavoriteOnDB.execute(
            viewModel,
            AddFavoriteOnDB.Params(
                FavoriteModel(
                    id = id,
                    favCoinName = input.favoriteModel.favCoinName,
                    favCoinRate = input.favoriteModel.favCoinRate,
                    url = input.favoriteModel.url
                )
            )
        )
    }
}
