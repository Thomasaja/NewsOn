package com.cimot.newson.ui.feature.home.detail

import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.local.room.datasource.FavoriteNewsDataSource
import com.cimot.newson.data.local.room.entity.FavoriteNews
import com.cimot.newson.data.network.datasource.news.NewsDataSource
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val favoriteNewsDataSource: FavoriteNewsDataSource,
    private val newsDataSource: NewsDataSource
):BaseRepositoryImpl(),DetailContract.Repository{
    override suspend fun insertNewsFavorite(favNews: FavoriteNews): Long {
        return favoriteNewsDataSource.insertFavoriteNews(favNews)
    }

    override suspend fun getNewsDetail(id: String): Article {
        return newsDataSource.getNewsDetail(id)
    }
}