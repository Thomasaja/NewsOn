package com.cimot.newson.data.network.datasource.news

import com.cimot.newson.data.model.response.news.NewsResponse
import com.cimot.newson.data.model.response.news.details.NewsDetails
import com.cimot.newson.data.network.services.NewsApiService
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(val newsApiService :NewsApiService):NewsDataSource {
    override suspend fun getAllNews(): NewsResponse {
        return newsApiService.getAllNews()
    }

    override suspend fun getNewsDetail(newsId: String): NewsDetails {
        return newsApiService.getNewsDetail(newsId)
    }
}