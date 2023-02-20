package com.cimot.newson.data.network.datasource.news

import com.cimot.newson.data.model.response.news.NewsResponse
import com.cimot.newson.data.model.response.news.details.Article

interface NewsDataSource {

    suspend fun getAllNews():NewsResponse
    suspend fun getNewsDetail(newsId:String):Article

}