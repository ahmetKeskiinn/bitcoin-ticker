package com.ahmetkeskin.bitcointicker.feature.home

import com.ahmetkeskin.bitcointicker.feature.home.data.HomeApiService
import com.ahmetkeskin.bitcointicker.feature.home.data.datasource.HomeDataSource
import com.ahmetkeskin.bitcointicker.feature.home.data.repo.HomeRepository
import com.ahmetkeskin.bitcointicker.feature.home.data.repo.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {

    @Singleton
    @Provides
    fun provideHomeApiService(retrofit: Retrofit): HomeApiService =
        retrofit.create(HomeApiService::class.java)

    @Singleton
    @Provides
    fun providesHomeDataSource(dataSource: HomeDataSource): HomeRepository {
        return HomeRepositoryImpl(dataSource)
    }
}