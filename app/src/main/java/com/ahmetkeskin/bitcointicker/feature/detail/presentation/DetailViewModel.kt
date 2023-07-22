package com.ahmetkeskin.bitcointicker.feature.detail.presentation

import com.ahmetkeskin.bitcointicker.base.BaseViewModel
import com.ahmetkeskin.bitcointicker.feature.detail.domain.AddFavorite
import com.ahmetkeskin.bitcointicker.feature.detail.domain.GetDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetail: GetDetail,
    private val addFavorite: AddFavorite
) : BaseViewModel() {

    fun addFavorite() = addFavorite.execute(this, Unit)

    fun getDetail(params: GetDetail.Params) = getDetail.execute(this, params)
}