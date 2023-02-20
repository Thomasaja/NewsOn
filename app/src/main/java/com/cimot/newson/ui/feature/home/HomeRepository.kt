package com.cimot.newson.ui.feature.home

import com.cimot.mycats.data.local.datasource.LocalAuthDataSource
import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.model.response.auth.User
import com.cimot.newson.data.model.response.news.NewsResponse
import com.cimot.newson.data.network.datasource.news.NewsDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val dataSource:NewsDataSource,
    private val localAuthDataSource: LocalAuthDataSource
):BaseRepositoryImpl(),HomeContract.Repository {
    override suspend fun getAllNews(): NewsResponse = dataSource.getAllNews()

    override fun deleteSession() {
       localAuthDataSource.clearSession()
    }

    override fun getUser(): User? {
        return localAuthDataSource.getUserData()
    }
}