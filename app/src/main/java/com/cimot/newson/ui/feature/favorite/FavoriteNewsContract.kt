package com.cimot.newson.ui.feature.favorite

import androidx.lifecycle.LiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.local.room.entity.FavoriteNews

interface FavoriteNewsContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getListData()
        fun getSearchData(searchQuery: String)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getNewsListLiveData(): LiveData<Resource<List<FavoriteNews>>>
        fun getAllNews()
        fun searchFavoriteNews(searchQuery: String)
        fun deleteFavoriteNews(favNews: FavoriteNews)
    }

    interface Repository:BaseContract.BaseRepository{
        suspend fun getAllFavoriteNews():List<FavoriteNews>
        suspend fun searchFavoriteNews(searchQuery:String):List<FavoriteNews>
        suspend fun deleteFavoriteNews(favNews :FavoriteNews):String
    }
}