package com.cimot.newson.ui.splash

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import javax.inject.Inject

class SplashScreenRepository @Inject constructor(
    private val authApiDataSource: AuthApiDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) : BaseRepositoryImpl(), SplashScreenContract.Repository {
    override suspend fun getSyncUser(): BaseAuthResponse<User, String> {
        return authApiDataSource.getSyncUser()
    }

    override fun isUserLoggedIn(): Boolean {
        return localAuthDataSource.isUserLoggedIn()
    }

    override fun clearSession() {
        localAuthDataSource.clearSession()
    }
}