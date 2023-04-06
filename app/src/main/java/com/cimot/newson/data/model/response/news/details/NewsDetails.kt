package com.cimot.newson.data.model.response.news.details


import com.google.gson.annotations.SerializedName

data class NewsDetails(
    @SerializedName("apiUrl")
    var apiUrl: String?,
    @SerializedName("id")
    var id: String?,
    @SerializedName("sectionId")
    var sectionId: String?,
    @SerializedName("sectionName")
    var sectionName: String?,
    @SerializedName("type")
    var type: String?,
    @SerializedName("webUrl")
    var webUrl: String?
)