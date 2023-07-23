package com.ahmetkeskin.bitcointicker.feature.detail

import com.ahmetkeskin.bitcointicker.feature.detail.data.DetailApiService
import com.ahmetkeskin.bitcointicker.feature.detail.data.repo.DetailRepository
import com.ahmetkeskin.bitcointicker.feature.detail.data.repo.DetailRepositoryImpl
import com.ahmetkeskin.bitcointicker.feature.detail.data.repo.datasource.DetailDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DetailModule {

    @Singleton
    @Provides
    fun provideDetailApiService(retrofit: Retrofit): DetailApiService =
        retrofit.create(DetailApiService::class.java)

    @Singleton
    @Provides
    fun providesDetailRepository(dataSource: DetailDataSource): DetailRepository {
        return DetailRepositoryImpl(dataSource)
    }
}
