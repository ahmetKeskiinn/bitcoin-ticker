package com.ahmetkeskin.bitcointicker.base.common.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_db")
data class FavoriteModel(
    var id: String? = null,
    @PrimaryKey
    val favCoinName: String,
    val favCoinRate: String
)