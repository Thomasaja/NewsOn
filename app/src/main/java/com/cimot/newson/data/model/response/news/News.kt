package com.cimot.newson.data.model.response.news


import com.cimot.newson.data.model.response.news.details.NewsDetails
import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("currentPage")
    var currentPage: Int?,
    @SerializedName("orderBy")
    var orderBy: String?,
    @SerializedName("pages")
    var pages: Int?,
    @SerializedName("results")
    var details: List<NewsDetails?>?,

)