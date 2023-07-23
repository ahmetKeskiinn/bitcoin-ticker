package com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo

import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.repo.datasource.SplashDataSource
import com.ahmetkeskin.bitcointicker.feature.auth.splash.data.response.UserModel
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val dataSource: SplashDataSource
) : SplashRepository {

    override suspend fun getLocalSettings() = dataSource.getLocalSettings()

}