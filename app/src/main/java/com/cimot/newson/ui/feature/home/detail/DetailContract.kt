package com.cimot.newson.ui.feature.home.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.cimot.newson.base.arch.BaseContract
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.local.room.entity.FavoriteNews

interface DetailContract {

    interface View : BaseContract.BaseView{
        fun setContentData(data: Article)
        fun getIntentData()
    }

    interface ViewModel : BaseContract.BaseViewModel{
        fun insertNewsFavorite(favNews: FavoriteNews)
        fun getNewsDetailResponse(): LiveData<Resource<Article>>
        fun getNewsId(): LiveData<String>
        fun setIntentData(extras : Bundle?)
        fun getNewsDetail(id: String)
    }

    interface Repository  : BaseContract.BaseRepository {
        suspend fun insertNewsFavorite(favNews: FavoriteNews) : Long
        suspend fun getNewsDetail(id: String): Article
    }
}