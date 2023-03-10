package com.cimot.mycats.data.network.datasource.auth

import com.cimot.newson.data.model.request.AuthRequest
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import com.cimot.newson.data.network.services.AuthApiServices
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class AuthApiDataSourceImpl
@Inject constructor(
    private val authApiService: AuthApiServices
): AuthApiDataSource{
    override suspend fun getSyncUser(): BaseAuthResponse<User, String> {
        return authApiService.getSyncUser()
    }

    override suspend fun getProfileData(): BaseAuthResponse<User, String> {
        return authApiService.getUserData()
    }

    override suspend fun postRegisterUser(registerRequest: AuthRequest): BaseAuthResponse<User, String> {
       return authApiService.postRegisterUser(registerRequest)
    }

    override suspend fun postLoginUser(loginRequest: AuthRequest): BaseAuthResponse<User, String> {
        return authApiService.postLoginUser(loginRequest)
    }

    override suspend fun updateProfileData(
        username: String,
        email: String,
        profilePhoto: File?
    ): BaseAuthResponse<User, String> {
        val requestBodyBuilder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("email", email)
            .addFormDataPart("username", username)
        if (profilePhoto != null){
            requestBodyBuilder.addFormDataPart(
                "photo", profilePhoto.name, RequestBody.create(
                    MediaType.parse("image/*"),
                    profilePhoto
                )
            )
        }
        return authApiService.putUserData(requestBodyBuilder.build())
    }
}