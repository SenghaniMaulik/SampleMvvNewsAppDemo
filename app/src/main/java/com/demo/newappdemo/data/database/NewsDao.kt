package com.demo.newappdemo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.newappdemo.data.database.entities.ArticleEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsList: List<ArticleEntity>)


    @Query("SELECT * FROM news")
    suspend fun getNewsList(): List<ArticleEntity>

}
















