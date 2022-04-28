package com.demo.newappdemo.utils


class Constant {
    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val DATABASE_NAME = "news_db"
    }

    interface API_RESPONSE_STATUS{
        companion object{
            const val OK = "ok"
        }
    }
    interface DATE{
        companion object{
            const val SERVER = "yyyy-mm-dd'T'hh:mm:ss+00:00"
            const val LOCAL = "yyyy-mm-dd HH:mm"
        }
    }
}