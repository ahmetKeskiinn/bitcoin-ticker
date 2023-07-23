package com.ahmetkeskin.bitcointicker.feature.auth.login

import com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo.LoginRepository
import com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo.LoginRepositoryImpl
import com.ahmetkeskin.bitcointicker.feature.auth.login.data.repo.datasource.LoginDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {
    @Singleton
    @Provides
    fun providesLoginRepository(dataSource: LoginDataSource): LoginRepository {
        return LoginRepositoryImpl(dataSource)
    }
}
