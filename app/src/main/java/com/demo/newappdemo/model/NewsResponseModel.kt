package com.demo.newappdemo.model

import com.demo.newappdemo.data.database.entities.ArticleEntity


data class NewsResponseModel(
    var articles: List<ArticleEntity>? = null,
    var status: String? = null,
    var message: String? = null,
    var totalResults: Int? = null
) {
}