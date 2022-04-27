package com.demo.newappdemo.data.database.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity(tableName = "news")
@Parcelize
data class ArticleEntity(
    val author: String? = "",
    val content: String? = "",
    val description: String? = "",
    val title: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    @PrimaryKey(autoGenerate = false)
    val publishedAt: String = ""
) : Parcelable
