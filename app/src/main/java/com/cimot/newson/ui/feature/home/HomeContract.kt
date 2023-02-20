package com.cimot.newson.ui.feature.home

import androidx.lifecycle.LiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.User
import com.cimot.newson.data.model.response.news.NewsResponse
import com.cimot.newson.data.model.response.news.details.Article

interface HomeContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getData()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getNewsLiveData(): LiveData<Resource<List<Article>>>
        fun getAllNews()
        fun deleteSession()
        fun getUser() : User?
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllNews():NewsResponse
        fun deleteSession()
        fun getUser() : User?
    }
}