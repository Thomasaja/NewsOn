package com.cimot.newson.data.model.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("email")
    var email:String? = null,
    @SerializedName("username")
    var username:String? = null,
    @SerializedName("password")
    var password:String? = null
)