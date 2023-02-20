package com.cimot.newson.ui.feature.home.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cimot.newson.base.arch.BaseViewModelImpl
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.local.room.entity.FavoriteNews
import com.cimot.newson.data.model.response.news.details.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) :
    BaseViewModelImpl(), DetailContract.ViewModel {

    private val newsId = MutableLiveData<String>()
    private val newsDetailLiveData = MutableLiveData<Resource<Article>>()
    private val insertNewsLiveData = MutableLiveData<Resource<String>>()

    override fun insertNewsFavorite(favNews: FavoriteNews) {
        insertNewsLiveData.value = Resource.Loading()
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.insertNewsFavorite(favNews)
                viewModelScope.launch (Dispatchers.Main){
                    insertNewsLiveData.value = Resource.Success(response)
                }
            } catch (e:Exception){
                viewModelScope.launch (Dispatchers.Main){
                    insertNewsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getNewsDetailResponse(): LiveData<Resource<Article>> = newsDetailLiveData

    override fun getNewsId(): LiveData<String> = newsId

    override fun setIntentData(extras: Bundle?) {
        if(extras != null){
            newsId.value = extras.getString(DetailActivity.EXTRAS_NEWS_DETAIL)
        }
    }

    override fun getNewsDetail(id: String) {
        newsDetailLiveData.value = Resource.Loading()
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.getNewsDetail(id)
                viewModelScope.launch (Dispatchers.Main){
                    newsDetailLiveData.value = Resource.Success(response)
                }
            } catch (e:Exception){
                viewModelScope.launch (Dispatchers.Main){
                    newsDetailLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }
}