package com.ahmetkeskin.bitcointicker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PrefManagerModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): PrefManager =
        PrefManager(context)
}
