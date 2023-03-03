package com.cimot.newson.data.local.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favNews")
data class FavoriteNews(
    @PrimaryKey
    var title:String? = null,
    @ColumnInfo(name = "image")
    var image:String? = null,
    @ColumnInfo(name = "description")
    var description:String? = null

):Parcelable
