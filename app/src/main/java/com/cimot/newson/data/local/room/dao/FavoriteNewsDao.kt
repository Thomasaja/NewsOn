package com.cimot.newson.data.local.room.dao

import androidx.room.*
import com.cimot.newson.data.local.room.entity.FavoriteNews


@Dao
interface FavoriteNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteNews(favNews : FavoriteNews):String

    @Delete
    suspend fun deleteFavoriteNews(favNews : FavoriteNews):String

    @Query("SELECT * FROM favNews")
    suspend fun getAllFavoriteNews():List<FavoriteNews>

    @Query("SELECT * FROM favNews WHERE name LIKE :searchQuery")
    suspend fun searchFavoriteNews(searchQuery :String):List<FavoriteNews>
}