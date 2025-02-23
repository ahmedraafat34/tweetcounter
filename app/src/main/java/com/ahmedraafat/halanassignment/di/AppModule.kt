package com.ahmedraafat.halanassignment.di

import com.ahmedraafat.halanassignment.data.apiservice.TwitterApiService
import com.ahmedraafat.halanassignment.data.repository.TweetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTweetRepository(
        apiService: TwitterApiService
    ): TweetRepository {
        return TweetRepository(apiService)
    }
}