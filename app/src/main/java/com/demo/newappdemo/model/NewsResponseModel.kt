package com.demo.newappdemo.model

data class NewsResponseModel(
    var articles: List<Article>? = null,
    var status: String? = null,
    var message: String? = null,
    var totalResults: Int? = null
) {
    data class Article(
        var author: String? = null,
        var content: String? = null,
        var description: String? = null,
        var publishedAt: String? = null,
        var source: Source? = null,
        var title: String? = null,
        var url: String? = null,
        var urlToImage: String? = null
    ) {
        data class Source(
            var id: String? = null,
            var name: String? = null
        )
    }
}