package com.cimot.newson.di

import com.cimot.newson.base.arch.GenericViewModelFactory
import com.cimot.newson.ui.feature.favorite.FavoriteNewsRepository
import com.cimot.newson.ui.feature.favorite.FavoriteNewsViewModel
import com.cimot.newson.ui.feature.home.HomeRepository
import com.cimot.newson.ui.feature.home.HomeViewModel
import com.cimot.newson.ui.feature.home.detail.DetailRepository
import com.cimot.newson.ui.feature.home.detail.DetailViewModel
import com.cimot.newson.ui.feature.login.LoginPageRepository
import com.cimot.newson.ui.feature.login.LoginPageViewModel
import com.cimot.newson.ui.feature.profile.ProfileRepository
import com.cimot.newson.ui.feature.profile.ProfileViewModel
import com.cimot.newson.ui.feature.profile.editprofile.EditProfileRepository
import com.cimot.newson.ui.feature.profile.editprofile.EditProfileViewModel
import com.cimot.newson.ui.feature.register.RegisterRepository
import com.cimot.newson.ui.feature.register.RegisterViewModel
import com.cimot.newson.ui.splash.SplashScreenRepository
import com.cimot.newson.ui.splash.SplashScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @ActivityScoped
    @Provides
    fun provideSplashScreenViewModel(splashScreenRepository: SplashScreenRepository): SplashScreenViewModel {
        return GenericViewModelFactory(SplashScreenViewModel(splashScreenRepository))
            .create(SplashScreenViewModel::class.java)
    }

    @Provides
    @ActivityScoped
    fun provideLoginPageViewModel(
        repository: LoginPageRepository
    ): LoginPageViewModel {
        return GenericViewModelFactory(LoginPageViewModel(repository)).create(
            LoginPageViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideRegisterViewModel(
        registerRepository: RegisterRepository
    ): RegisterViewModel {
        return GenericViewModelFactory(RegisterViewModel(registerRepository)).create(
            RegisterViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideDetailViewModel(detailRepository: DetailRepository): DetailViewModel {
        return GenericViewModelFactory(DetailViewModel(detailRepository)).create(
            DetailViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideNewsListViewModel(
        homeRepository: HomeRepository
    ): HomeViewModel {
        return GenericViewModelFactory(HomeViewModel(homeRepository)).create(
            HomeViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideFavoriteNewsViewModel(favoriteNewsRepository: FavoriteNewsRepository): FavoriteNewsViewModel {
        return GenericViewModelFactory(FavoriteNewsViewModel(favoriteNewsRepository)).create(
            FavoriteNewsViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideProfileViewModel(
        repository: ProfileRepository
    ): ProfileViewModel {
        return GenericViewModelFactory(ProfileViewModel(repository)).create(
            ProfileViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideEditProfileViewModel(
        repository: EditProfileRepository
    ): EditProfileViewModel {
        return GenericViewModelFactory(EditProfileViewModel(repository)).create(
            EditProfileViewModel::class.java
        )
    }
}