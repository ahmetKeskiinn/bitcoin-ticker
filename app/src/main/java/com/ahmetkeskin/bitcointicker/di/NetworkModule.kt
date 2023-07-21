package com.ahmetkeskin.bitcointicker.di

import com.ahmetkeskin.bitcointicker.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttp: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttp)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

}