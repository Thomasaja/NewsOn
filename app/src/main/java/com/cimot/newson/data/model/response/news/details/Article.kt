package com.cimot.newson.data.model.response.news.details


import com.google.gson.annotations.SerializedName

data class Article(

    @SerializedName("description")
    var description: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("title")
    var title: String?,
)
