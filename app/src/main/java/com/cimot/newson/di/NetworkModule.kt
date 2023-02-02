package com.cimot.newson.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.newson.data.network.services.AuthApiServices
import com.cimot.newson.data.network.services.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideAuthApiServices(
        localAuthDataSource: LocalAuthDataSource,
        chuckerInterceptor: ChuckerInterceptor
    ): AuthApiServices {
        return AuthApiServices.invoke(localAuthDataSource, chuckerInterceptor)
    }

    @Singleton
    @Provides
    fun provideNewsApiServices(chuckerInterceptor: ChuckerInterceptor): NewsApiService {
        return NewsApiService.invoke(chuckerInterceptor)
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}