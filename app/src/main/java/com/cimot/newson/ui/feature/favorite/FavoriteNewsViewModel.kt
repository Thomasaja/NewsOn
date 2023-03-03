package com.cimot.newson.ui.feature.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cimot.newson.base.arch.BaseViewModelImpl
import com.cimot.newson.base.model.Resource
import com.cimot.newson.data.local.room.entity.FavoriteNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


@HiltViewModel
class FavoriteNewsViewModel @Inject constructor(private val repository: FavoriteNewsRepository) :
    BaseViewModelImpl(), FavoriteNewsContract.ViewModel {

    private val allNewsLiveData = MutableLiveData<Resource<List<FavoriteNews>>>()

    override fun getNewsListLiveData(): LiveData<Resource<List<FavoriteNews>>> = allNewsLiveData

    override fun getAllNews() {
        allNewsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val favoriteNews = repository.getAllFavoriteNews()
                viewModelScope.launch(Dispatchers.Main) {
                    allNewsLiveData.value = Resource.Success(favoriteNews)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    allNewsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun searchFavoriteNews(searchQuery: String) {
        allNewsLiveData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val searchNews = repository.searchFavoriteNews(searchQuery)
                viewModelScope.launch(Dispatchers.Main) {
                    allNewsLiveData.value = Resource.Success(searchNews)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    allNewsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun deleteFavoriteNews(favNews: FavoriteNews) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteNews(favNews)
        }
    }
}