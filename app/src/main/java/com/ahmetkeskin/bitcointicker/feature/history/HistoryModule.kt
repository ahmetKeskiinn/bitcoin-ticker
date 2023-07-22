package com.ahmetkeskin.bitcointicker.feature.history

import com.ahmetkeskin.bitcointicker.feature.history.data.HistoryApiService
import com.ahmetkeskin.bitcointicker.feature.history.data.repo.HistoryRepository
import com.ahmetkeskin.bitcointicker.feature.history.data.repo.HistoryRepositoryImpl
import com.ahmetkeskin.bitcointicker.feature.history.data.repo.datasource.HistoryDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HistoryModule {

    @Singleton
    @Provides
    fun provideDetailApiService(retrofit: Retrofit): HistoryApiService =
        retrofit.create(HistoryApiService::class.java)

    @Singleton
    @Provides
    fun providesDetailRepository(dataSource: HistoryDataSource): HistoryRepository {
        return HistoryRepositoryImpl(dataSource)
    }

}