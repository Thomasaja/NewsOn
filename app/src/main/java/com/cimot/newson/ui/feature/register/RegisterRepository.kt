package com.cimot.newson.ui.feature.register

import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import javax.inject.Inject

class RegisterRepository
@Inject constructor(
  private val authApiDataSource: AuthApiDataSource
):BaseRepositoryImpl(),RegisterContract.Repository{
    override suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User, String> {
        return authApiDataSource.postRegisterUser(registerRequest)
    }
}