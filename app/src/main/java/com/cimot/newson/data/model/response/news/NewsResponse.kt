package com.cimot.newson.data.model.response.news


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<Article?>?,
    @SerializedName("totalArticles")
    var totalArticles: Int?
)