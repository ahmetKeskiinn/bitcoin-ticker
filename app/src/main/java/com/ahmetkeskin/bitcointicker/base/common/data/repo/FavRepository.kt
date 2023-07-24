package com.ahmetkeskin.bitcointicker.base.common.data.repo

import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel

interface FavRepository {

    fun getFavorites(): List<FavoriteModel>

    suspend fun insertFavorite(model: FavoriteModel)

    suspend fun deleteFavorite(model: FavoriteModel)

    suspend fun checkFav(name: String): List<FavoriteModel>

    suspend fun dropDB()

    suspend fun searchFav(name: String): List<FavoriteModel>
}
