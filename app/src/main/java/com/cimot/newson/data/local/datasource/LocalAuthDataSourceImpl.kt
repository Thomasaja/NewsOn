package com.cimot.mycats.data.local.datasource

import SessionPreference

import com.cimot.newson.data.model.response.auth.User
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(private val sessionPreference:SessionPreference) : LocalAuthDataSource {
    override fun getAuthToken(): String? {
        return sessionPreference.authToken
    }

    override fun setAuthToken(authToken: String?) {
        sessionPreference.authToken = authToken
    }

    override fun isUserLoggedIn(): Boolean {
        return !sessionPreference.authToken.isNullOrEmpty()
    }

    override fun saveUserData(user: User?) {
        sessionPreference.user = user
    }

    override fun getUserData(): User? {
        return sessionPreference.user
    }

    override fun clearSession() {
        sessionPreference.deleteSession()
    }
}