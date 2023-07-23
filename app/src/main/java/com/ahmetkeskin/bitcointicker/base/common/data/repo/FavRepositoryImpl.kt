package com.ahmetkeskin.bitcointicker.base.common.data.repo

import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteDao
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteModel
import javax.inject.Inject

class FavRepositoryImpl @Inject constructor(private val favDao: FavoriteDao) : FavRepository {

    override fun getFavorites() = favDao.fetchFavorite()

    override suspend fun insertFavorite(model: FavoriteModel) = favDao.insertFavorite(model)

    override suspend fun deleteFavorite(model: FavoriteModel) = favDao.deleteFavorite(model)

    override suspend fun checkFav(name: String) = favDao.checkDatabase(name)

}
