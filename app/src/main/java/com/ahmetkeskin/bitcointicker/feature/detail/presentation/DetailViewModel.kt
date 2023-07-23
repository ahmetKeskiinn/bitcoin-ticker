package com.ahmetkeskin.bitcointicker.feature.detail.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.AddFavoriteOnFB
import com.ahmetkeskin.bitcointicker.base.common.domain.firebase.RemoveFavoriteOnFB
import com.ahmetkeskin.bitcointicker.base.common.domain.room.CheckFavoriteOnDB
import com.ahmetkeskin.bitcointicker.feature.detail.domain.GetDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetail: GetDetail,
    private val addFavoriteOnFB: AddFavoriteOnFB,
    private val checkFavoriteOnDB: CheckFavoriteOnDB,
    private val removeFavoriteOnFB: RemoveFavoriteOnFB
) : BaseViewModel() {

    private val _isFollowing = MutableLiveData<Boolean>(false)
    val isFollowing: LiveData<Boolean> = _isFollowing
    fun checkFollowing(params: CheckFavoriteOnDB.Params) =
        checkFavoriteOnDB.execute(this, params)

    fun setFollowingState(isFollowing: Boolean) {
        _isFollowing.value = isFollowing
    }

    fun addFavorite(favModel: AddFavoriteOnFB.Params) = addFavoriteOnFB.execute(this, favModel)
    fun removeFavorite(favModel: RemoveFavoriteOnFB.Params) =
        removeFavoriteOnFB.execute(this, favModel)

    fun getDetail(params: GetDetail.Params) = getDetail.execute(this, params)
}
