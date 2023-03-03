package com.cimot.newson.di

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.newson.data.local.room.datasource.FavoriteNewsDataSource
import com.cimot.newson.data.network.datasource.news.NewsDataSource
import com.cimot.newson.ui.feature.favorite.FavoriteNewsRepository
import com.cimot.newson.ui.feature.home.HomeRepository
import com.cimot.newson.ui.feature.home.detail.DetailRepository
import com.cimot.newson.ui.feature.login.LoginPageRepository
import com.cimot.newson.ui.feature.profile.ProfileRepository
import com.cimot.newson.ui.feature.profile.editprofile.EditProfileRepository
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
        authApiDataSource: AuthApiDataSource,
        localAuthDataSource: LocalAuthDataSource
    ): SplashScreenRepository {
        return SplashScreenRepository(authApiDataSource, localAuthDataSource)
    }

    @Singleton
    @Provides
    fun provideLoginPageRepository(
        authApiDataSource: AuthApiDataSource,
        localAuthDataSource: LocalAuthDataSource
    ): LoginPageRepository {
        return LoginPageRepository(authApiDataSource, localAuthDataSource)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(
        favoriteNewsDataSource: FavoriteNewsDataSource, newsDataSource: NewsDataSource
    ): DetailRepository {
        return DetailRepository(favoriteNewsDataSource, newsDataSource)
    }

    @Singleton
    @Provides
    fun provideNewsListRepository(
        newsDataSource: NewsDataSource,
        localAuthDataSource: LocalAuthDataSource
    ): HomeRepository {
        return HomeRepository(newsDataSource, localAuthDataSource)
    }

    @Singleton
    @Provides
    fun provideFavoriteNewsRepository(
        favoriteNewsDataSource: FavoriteNewsDataSource
    ): FavoriteNewsRepository {
        return FavoriteNewsRepository(favoriteNewsDataSource)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        localAuthDataSource: LocalAuthDataSource
    ): ProfileRepository {
        return ProfileRepository(localAuthDataSource)
    }

    @Singleton
    @Provides
    fun provideEditProfileRepository(
        authApiDataSource: AuthApiDataSource,
        localAuthDataSource: LocalAuthDataSource
    ): EditProfileRepository {
        return EditProfileRepository(authApiDataSource,localAuthDataSource)
    }
}