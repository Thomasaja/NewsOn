package com.cimot.newson.data.local.room.datasource

import com.cimot.newson.data.local.room.dao.FavoriteNewsDao
import com.cimot.newson.data.local.room.entity.FavoriteNews
import javax.inject.Inject

class FavoriteNewsDataSourceImpl @Inject constructor(private val dao:FavoriteNewsDao):FavoriteNewsDataSource{
    override suspend fun insertFavoriteNews(favNews: FavoriteNews): Int {
        return dao.insertFavoriteNews(favNews)
    }

    override suspend fun deleteFavoriteNews(favNews: FavoriteNews): Int {
        return dao.deleteFavoriteNews(favNews)
    }

    override suspend fun getAllFavoriteNews(): List<FavoriteNews> {
        return dao.getAllFavoriteNews()
    }

    override suspend fun searchFavoriteNews(searchQuery: String): List<FavoriteNews> {
        return dao.searchFavoriteNews(searchQuery)
    }
}