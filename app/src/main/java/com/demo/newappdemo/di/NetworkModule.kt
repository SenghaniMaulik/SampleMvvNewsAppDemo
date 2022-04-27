package com.demo.newappdemo.di

import android.content.Context
import androidx.room.Room
import com.demo.newappdemo.BuildConfig
import com.demo.newappdemo.data.database.NewsDatabase
import com.demo.newappdemo.data.network.ApiInterface
import com.demo.newappdemo.utils.Constant.Companion.BASE_URL
import com.demo.newappdemo.utils.Constant.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    class SupportInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .build()
            return chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun providerHttpClient(): OkHttpClient {
        val interceptor = SupportInterceptor()
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideNotesDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, NewsDatabase::class.java, DATABASE_NAME)
        .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideNoteDao(db: NewsDatabase) = db.newsDao()
}