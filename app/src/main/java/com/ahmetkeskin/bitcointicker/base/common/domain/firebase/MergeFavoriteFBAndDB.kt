package com.ahmetkeskin.bitcointicker.base.common.domain.firebase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ahmetkeskin.bitcointicker.base.BaseUseCase
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import com.ahmetkeskin.bitcointicker.base.common.domain.room.AddFavoriteOnDB
import com.ahmetkeskin.bitcointicker.base.common.domain.room.DropDB
import com.ahmetkeskin.bitcointicker.base.extensions.FAVORITE_CURRENCY
import com.ahmetkeskin.bitcointicker.base.extensions.FAVORITE_CURRENCY_RATE
import com.ahmetkeskin.bitcointicker.base.extensions.URL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import javax.inject.Inject

class MergeFavoriteFBAndDB @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
    private val addFavoriteOnDB: AddFavoriteOnDB,
    private val dropDB: DropDB
) : BaseUseCase<MutableLiveData<Boolean?>, Unit>() {

    override fun execute(
        viewModel: BaseViewModel,
        input: Unit?
    ): MutableLiveData<Boolean?> {
        return MutableLiveData<Boolean?>().apply {
            viewModel.viewModelScope.launch {
                firebaseAuth.currentUser?.let { user ->
                    fireStore.collection(user.uid).get().addOnSuccessListener { success ->
                        dropDB(viewModel)
                        if (success.documents.isNotEmpty()) {
                            success.documents.forEach {
                                val model = FavoriteModel(
                                    id = it.id,
                                    favCoinName = it.get(FAVORITE_CURRENCY).toString(),
                                    favCoinRate = it.get(FAVORITE_CURRENCY_RATE).toString(),
                                    url = it.get(URL).toString()
                                )
                                addToDB(viewModel, model)
                            }
                        }
                        value = true
                    }.addOnFailureListener {
                        value = false
                    }
                }
            }
        }
    }

    private fun dropDB(viewModel: BaseViewModel) {
        dropDB.execute(viewModel = viewModel, Unit)
    }

    private fun addToDB(viewModel: BaseViewModel, favModel: FavoriteModel) {
        addFavoriteOnDB.execute(
            viewModel,
            AddFavoriteOnDB.Params(
                favModel
            )
        )
    }
}
