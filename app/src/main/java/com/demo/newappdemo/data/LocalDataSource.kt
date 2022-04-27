package com.demo.newappdemo.data

import com.demo.newappdemo.data.database.NewsDao
import com.demo.newappdemo.data.database.entities.ArticleEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend fun insertNewsList(keyFeatureList: List<ArticleEntity>) {
        return newsDao.insertNews(keyFeatureList)
    }

    suspend fun getNewsList(): List<ArticleEntity> {
        return newsDao.getNewsList()
    }
}