package com.cimot.newson.data.model.response.news


import com.cimot.newson.data.model.response.news.details.Article
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<Article>?,
    @SerializedName("totalArticles")
    var totalArticles: Int?
)