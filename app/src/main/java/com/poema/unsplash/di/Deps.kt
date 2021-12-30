package com.poema.unsplash.di

import com.poema.unsplash.data.UnSplashApi
import com.poema.unsplash.data.UnSplashApi.Companion.BASE_URL
import com.poema.unsplash.data.repository.Repository
import com.poema.unsplash.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Deps {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideUnSplashApi(retrofit: Retrofit.Builder): UnSplashApi {
        return retrofit
            .build()
            .create(UnSplashApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(api: UnSplashApi): Repository {
        return RepositoryImpl(api = api)
    }

}