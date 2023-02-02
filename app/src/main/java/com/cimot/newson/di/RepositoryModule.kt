package com.cimot.newson.di

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.newson.ui.feature.login.LoginPageRepository
import com.cimot.newson.ui.splash.SplashScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideSplashScreenRepository(
        authApiDataSource:AuthApiDataSource,
        localAuthDataSource: LocalAuthDataSource
    ):SplashScreenRepository{
        return SplashScreenRepository(authApiDataSource,localAuthDataSource)
    }

    @Singleton
    @Provides
    fun provideLoginPageRepository(
        authApiDataSource: AuthApiDataSource,
        localAuthDataSource: LocalAuthDataSource
    ):LoginPageRepository{
        return LoginPageRepository(authApiDataSource,localAuthDataSource)
    }
}