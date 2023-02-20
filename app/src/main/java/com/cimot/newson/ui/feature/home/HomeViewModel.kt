package com.cimot.newson.ui.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cimot.newson.base.arch.BaseViewModelImpl
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.model.response.auth.User
import com.cimot.newson.data.model.response.news.NewsResponse
import com.cimot.newson.data.model.response.news.details.Article
import com.cimot.newson.data.network.datasource.news.NewsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): BaseViewModelImpl(),HomeContract.ViewModel{

    private val newsLiveData = MutableLiveData<Resource<List<Article>>>()
    override fun getNewsLiveData(): LiveData<Resource<List<Article>>> {
        return newsLiveData
    }

    override fun getAllNews() {
        newsLiveData.value = Resource.Loading()
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response = repository.getAllNews()
                viewModelScope.launch (Dispatchers.Main){
                    newsLiveData.value = response.articles?.let { Resource.Success(it) }!!
                }
            } catch (e:Exception){
                viewModelScope.launch (Dispatchers.Main){
                    newsLiveData.value = Resource.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }

    override fun deleteSession() {
        repository.deleteSession()
    }

    override fun getUser(): User? {
       return repository.getUser()
    }
}