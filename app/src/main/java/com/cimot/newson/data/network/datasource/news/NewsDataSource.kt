package com.cimot.newson.data.network.datasource.news

import com.cimot.newson.data.model.response.news.NewsResponse

interface NewsDataSource {

    suspend fun getAllNews():NewsResponse

}