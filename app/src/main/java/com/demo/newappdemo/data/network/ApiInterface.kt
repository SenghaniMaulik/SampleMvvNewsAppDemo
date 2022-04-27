package com.demo.newappdemo.data.network

import com.demo.newappdemo.model.NewsResponseModel
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    companion object {
        const val TOP_HEADLINES = "top-headlines?sources=google-news&apiKey=19eb9d278f9041fca7e25bdd53686598"

    }

    @GET(TOP_HEADLINES)
    suspend fun getNews(
    ): Response<NewsResponseModel>

}