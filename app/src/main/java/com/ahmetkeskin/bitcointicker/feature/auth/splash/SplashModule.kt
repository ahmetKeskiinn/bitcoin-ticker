package com.ahmetkeskin.bitcointicker.feature.auth.splash

import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo.SplashRepository
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo.SplashRepositoryImpl
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo.datasource.SplashDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SplashModule {

    @Singleton
    @Provides
    fun providesSplashRepository(dataSource: SplashDataSource): SplashRepository {
        return SplashRepositoryImpl(dataSource)
    }
}
