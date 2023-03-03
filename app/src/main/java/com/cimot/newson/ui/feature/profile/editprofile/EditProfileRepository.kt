package com.cimot.newson.ui.feature.profile.editprofile

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.mycats.data.network.datasource.auth.AuthApiDataSource
import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.model.response.auth.BaseAuthResponse
import com.cimot.newson.data.model.response.auth.User
import java.io.File
import javax.inject.Inject
import kotlin.math.atan

class EditProfileRepository @Inject constructor(
    private val authApiDataSource: AuthApiDataSource,
    private val localAuthDataSource: LocalAuthDataSource
) : BaseRepositoryImpl(), EditProfileContract.Repository {
    override suspend fun saveCacheProfileData(response: BaseAuthResponse<User, String>) {
        if (response.issuccess) {
            val modifiedUserData = localAuthDataSource.getUserData()?.apply {
                id = response.data.id
                email = response.data.email
                username = response.data.username
                photo = response.data.photo
            }
            modifiedUserData?.let {
                localAuthDataSource.saveUserData(it)
            }
        }
    }

    override suspend fun getProfileData(): BaseAuthResponse<User, String> {
        val response = authApiDataSource.getProfileData()
        saveCacheProfileData(response)
        return response
    }

    override suspend fun updateProfileData(
        email: String,
        username: String,
        photoProfile: File?
    ): BaseAuthResponse<User, String> {
        val response = authApiDataSource.updateProfileData(username, email, photoProfile)
        saveCacheProfileData(response)
        return response
    }
}