package com.cimot.newson.ui.feature.login

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import javax.inject.Inject

class LoginPageRepository @Inject constructor(
    private val authApiDataSource: AuthApiDataSource,
    private val localAuthDataSource: LocalAuthDataSource

) : BaseRepositoryImpl(), LoginPageContract.Repository {
    override suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User, String> {
        val loginResponse = authApiDataSource.postLoginUser(loginRequest)
        if (loginResponse.issuccess) {
            localAuthDataSource.setAuthToken(loginResponse.data.token)
            val userResponse = authApiDataSource.getProfileData()
            localAuthDataSource.saveUserData(userResponse.data)
        }
        return loginResponse
    }
}