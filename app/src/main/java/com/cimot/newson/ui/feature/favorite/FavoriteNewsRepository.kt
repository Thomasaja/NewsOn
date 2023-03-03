package com.cimot.newson.ui.feature.favorite

import com.cimot.newson.base.arch.BaseRepositoryImpl
import com.cimot.newson.data.local.room.datasource.FavoriteNewsDataSource
import com.cimot.newson.data.local.room.entity.FavoriteNews
import javax.inject.Inject

class FavoriteNewsRepository @Inject constructor(private val favoriteNewsDataSource: FavoriteNewsDataSource) :
    BaseRepositoryImpl(),
    FavoriteNewsContract.Repository {
    override suspend fun getAllFavoriteNews(): List<FavoriteNews> {
        return favoriteNewsDataSource.getAllFavoriteNews()
    }

    override suspend fun searchFavoriteNews(searchQuery: String): List<FavoriteNews> {
        return favoriteNewsDataSource.searchFavoriteNews(searchQuery)
    }

    override suspend fun deleteFavoriteNews(favNews: FavoriteNews): String {
        return favoriteNewsDataSource.deleteFavoriteNews(favNews)
    }
}