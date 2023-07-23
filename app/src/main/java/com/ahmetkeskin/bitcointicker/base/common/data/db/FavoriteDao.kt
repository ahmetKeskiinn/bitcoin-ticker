package com.ahmetkeskin.bitcointicker.base.common.data.db

import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM fav_db")
    fun fetchFavorite(): List<FavoriteModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(fav: FavoriteModel)

    @Query("SELECT * FROM fav_db WHERE favCoinName LIKE :searchQuery")
    fun checkDatabase(searchQuery: String): List<FavoriteModel>

    @Delete
    suspend fun deleteFavorite(fav: FavoriteModel)
}