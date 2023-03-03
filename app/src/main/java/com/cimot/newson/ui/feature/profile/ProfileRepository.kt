package com.cimot.newson.ui.feature.profile

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.model.response.auth.User
import javax.inject.Inject

class ProfileRepository
@Inject constructor(private val localAuthDataSource: LocalAuthDataSource) : BaseRepositoryImpl(),
    ProfileContract.Repository {
    override suspend fun getProfileData(): User? {
        return localAuthDataSource.getUserData()
    }

    override fun clearSession() {
        localAuthDataSource.clearSession()
    }
}