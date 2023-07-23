package com.ahmetkeskin.bitcointicker.di

import android.content.Context
import androidx.room.Room
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteDao
import com.ahmetkeskin.bitcointicker.base.common.data.db.FavoriteDataBase
import com.ahmetkeskin.bitcointicker.base.common.data.repo.FavRepository
import com.ahmetkeskin.bitcointicker.base.common.data.repo.FavRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Singleton
    @Provides
    fun providesFavDatabase(@ApplicationContext context: Context): FavoriteDataBase {
        return Room.databaseBuilder(context, FavoriteDataBase::class.java, FavoriteDataBase.DB_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun providesFavDao(favDataBase: FavoriteDataBase) = favDataBase.getFavDao()

    @Singleton
    @Provides
    fun providesFavRepository(notesDao: FavoriteDao): FavRepository {
        return FavRepositoryImpl(notesDao)
    }
}