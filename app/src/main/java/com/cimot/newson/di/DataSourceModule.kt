package com.cimot.newson.di

import SessionPreference
import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.mycats.data.local.datasource.LocalAuthDataSourceImpl
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSourceImpl
import com.cimot.newson.data.local.room.dao.FavoriteNewsDao
import com.cimot.newson.data.local.room.datasource.FavoriteNewsDataSource
import com.cimot.newson.data.local.room.datasource.FavoriteNewsDataSourceImpl
import com.cimot.newson.data.network.datasource.news.NewsDataSource
import com.cimot.newson.data.network.datasource.news.NewsDataSourceImpl
import com.cimot.newson.data.network.services.AuthApiServices
import com.cimot.newson.data.network.services.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideAuthDataSource(authApiServices: AuthApiServices):AuthApiDataSource{
        return AuthApiDataSourceImpl(authApiServices)
    }

    @Singleton
    @Provides
    fun provideNewsDataSource(newsApiService: NewsApiService):NewsDataSource{
        return NewsDataSourceImpl(newsApiService)
    }

    @Singleton
    @Provides
    fun provideFavoriteNewsDataSource(favoriteNewsDao: FavoriteNewsDao):FavoriteNewsDataSource{
        return FavoriteNewsDataSourceImpl(favoriteNewsDao)
    }

    @Singleton
    @Provides
    fun provideLocalAuthDataSource(userPreference: SessionPreference):LocalAuthDataSource{
        return LocalAuthDataSourceImpl(userPreference)
    }
}