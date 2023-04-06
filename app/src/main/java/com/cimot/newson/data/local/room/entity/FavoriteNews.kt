package com.cimot.newson.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cimot.newson.data.model.response.news.details.NewsDetails
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favNews")
data class FavoriteNews(
    @PrimaryKey
    var currentPage: Int? = null,
    @ColumnInfo
    var orderBy: String? = null,
    @ColumnInfo
    var pages: Int? = null,


    ) : Parcelable
