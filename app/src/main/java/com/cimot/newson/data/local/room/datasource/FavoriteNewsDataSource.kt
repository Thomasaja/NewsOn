package com.cimot.newson.data.local.room.datasource

import com.cimot.newson.data.local.room.entity.FavoriteNews

interface FavoriteNewsDataSource {

    suspend fun insertFavoriteNews(favNews: FavoriteNews):String
    suspend fun deleteFavoriteNews(favNews: FavoriteNews):String
    suspend fun getAllFavoriteNews(): List<FavoriteNews>
    suspend fun searchFavoriteNews(searchQuery:String):List<FavoriteNews>
}