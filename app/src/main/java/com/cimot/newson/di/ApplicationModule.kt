package com.cimot.newson.di

import SessionPreference
import android.content.Context
import androidx.room.Room
import com.cimot.newson.data.local.room.FavNewsDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavNewsDatabase::class.java, "favorite_news_db").build()
}

@Singleton
@Provides
fun provideDao(database: FavNewsDatabase) = database.favoriteNewsDao()

@Singleton
@Provides
fun provideSessionPreference(
    @ApplicationContext context: Context,
    gson: Gson
): SessionPreference {
    return SessionPreference(context, gson)
}

@Singleton
@Provides
fun provideGson(): Gson {
    return Gson()
}