package com.cimot.newson.data.model.response.news


import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("name")
    var name: String?,
)