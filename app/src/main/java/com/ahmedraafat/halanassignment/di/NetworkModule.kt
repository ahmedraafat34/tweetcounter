package com.ahmedraafat.halanassignment.di

import com.ahmedraafat.halanassignment.BuildConfig
import com.ahmedraafat.halanassignment.data.OAuthInterceptor
import com.ahmedraafat.halanassignment.data.apiservice.TwitterApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val API_KEY =  BuildConfig.TWITTER_API_KEY
    private const val API_SECRET = BuildConfig.TWITTER_API_SECRET
    private const val ACCESS_TOKEN =  BuildConfig.ACCESS_TOKEN
    private const val ACCESS_SECRET = BuildConfig.ACCESS_SECRET

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            OAuthInterceptor(
                apiKey = API_KEY,
                apiSecret = API_SECRET,
                accessToken = ACCESS_TOKEN,
                accessSecret = ACCESS_SECRET
            )
        )
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.twitter.com/2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TwitterApiService {
        return retrofit.create(TwitterApiService::class.java)
    }
}