package com.ahmetkeskin.bitcointicker.base.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteModel::class], version = 2, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase() {

    abstract fun getFavDao(): FavoriteDao

    companion object {
        const val DB_NAME = "fav_db"
    }
}