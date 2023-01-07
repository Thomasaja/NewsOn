package com.cimot.newson.data.model.response.news


import com.google.gson.annotations.SerializedName

data class Article(
   /* @SerializedName("content")
    var content: String?,*/
    @SerializedName("description")
    var description: String?,
    @SerializedName("image")
    var image: String?,
   /* @SerializedName("source")
    var source: Source?,*/
    @SerializedName("title")
    var title: String?,
)
