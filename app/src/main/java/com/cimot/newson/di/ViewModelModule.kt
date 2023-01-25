package com.cimot.newson.di

import com.cimot.newson.base.arch.GenericViewModelFactory
import com.cimot.newson.ui.splash.SplashScreenRepository
import com.cimot.newson.ui.splash.SplashScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @ActivityScoped
    @Provides
    fun provideSplashScreenViewModel(splashScreenRepository: SplashScreenRepository): SplashScreenViewModel {
        return GenericViewModelFactory(SplashScreenViewModel(splashScreenRepository))
            .create(SplashScreenViewModel::class.java)
    }
}