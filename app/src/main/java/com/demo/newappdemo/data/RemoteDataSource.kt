package com.demo.newappdemo.data

import android.app.Application
import com.demo.newappdemo.data.network.ApiInterface
import com.demo.newappdemo.model.NewsResponseModel
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface,
    val application: Application,
) {

    suspend fun getNews(): Response<NewsResponseModel> {
        return apiInterface.getNews()
    }


}