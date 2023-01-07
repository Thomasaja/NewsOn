package com.cimot.mycats.data.local.datasource


import com.cimot.newson.data.model.response.auth.User

interface LocalAuthDataSource {
    fun getAuthToken():String?
    fun setAuthToken(authToken:String?)
    fun isUserLoggedIn():Boolean
    fun saveUserData(user: User?)
    fun getUserData():User?
    fun clearSession()
}