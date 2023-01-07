package com.cimot.newson.data.network.services

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.cimot.newson.BuildConfig
import com.cimot.newson.BuildConfig.API_KEY
import com.cimot.newson.data.model.response.news.NewsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface NewsApiService {

    @GET("random/")
    suspend fun getAllNews(
        @Query("apiKey") key:String = BuildConfig.API_KEY,
        @Query("number") number: Int = 100
    ):NewsResponse


    companion object{
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor):NewsApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120,TimeUnit.SECONDS)
                .readTimeout(120,TimeUnit.SECONDS)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(NewsApiService::class.java)
        }
    }

}