package com.cimot.newson.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cimot.newson.data.local.room.dao.FavoriteNewsDao
import com.cimot.newson.data.local.room.entity.FavoriteNews


@Database(entities = [FavoriteNews::class], version = 1, exportSchema = false)
abstract class FavNewsDatabase :RoomDatabase() {
    abstract fun favoriteNewsDao():FavoriteNewsDao
}