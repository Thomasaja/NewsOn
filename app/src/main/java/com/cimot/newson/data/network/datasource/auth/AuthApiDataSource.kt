package com.cimot.mycats.data.network.datasource.auth

import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import java.io.File

interface AuthApiDataSource {

    suspend fun getSyncUser():BaseAuthResponse<User,String>
    suspend fun getProfileData():BaseAuthResponse<User,String>
    suspend fun postRegisterUser(registerRequest: AuthRequest):BaseAuthResponse<User,String>
    suspend fun postLoginUser(loginRequest: AuthRequest):BaseAuthResponse<User,String>
    suspend fun updateProfileData(
        username:String,
        email:String,
        profilePhoto: File? = null
    ): BaseAuthResponse<User, String>
}