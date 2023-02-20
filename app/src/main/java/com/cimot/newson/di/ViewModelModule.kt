package com.cimot.newson.di

import com.cimot.newson.base.arch.GenericViewModelFactory
import com.cimot.newson.ui.feature.home.detail.DetailRepository
import com.cimot.newson.ui.feature.home.detail.DetailViewModel
import com.cimot.newson.ui.feature.login.LoginPageRepository
import com.cimot.newson.ui.feature.login.LoginPageViewModel
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
    ):LoginPageViewModel{
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
    @Singleton
    fun provideDetailViewModel(detailRepository: DetailRepository):DetailViewModel{
        return GenericViewModelFactory(DetailViewModel(detailRepository)).create(
            DetailViewModel::class.java
        )
    }
}