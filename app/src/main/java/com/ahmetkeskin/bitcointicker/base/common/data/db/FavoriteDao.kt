package com.ahmetkeskin.bitcointicker.base.common.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM fav_db")
    fun fetchFavorite(): List<FavoriteModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(fav: FavoriteModel)

    @Query("SELECT * FROM fav_db WHERE favCoinName LIKE :searchQuery")
    fun checkDatabase(searchQuery: String): List<FavoriteModel>

    @Query("SELECT * FROM fav_db WHERE favCoinName LIKE '%' || :searchQuery || '%'")
    fun searchFavorite(searchQuery: String): List<FavoriteModel>

    @Delete
    suspend fun deleteFavorite(fav: FavoriteModel)
}
