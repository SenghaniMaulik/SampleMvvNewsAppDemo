package com.demo.newappdemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.newappdemo.data.database.entities.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 3
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}