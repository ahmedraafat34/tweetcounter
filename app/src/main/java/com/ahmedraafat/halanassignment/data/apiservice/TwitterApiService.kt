package com.ahmedraafat.halanassignment.data.apiservice

import com.ahmedraafat.halanassignment.data.model.TweetRequest
import com.ahmedraafat.halanassignment.data.model.TweetResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TwitterApiService {
    @POST("tweets")
    suspend fun postTweet(@Body tweet: TweetRequest): Response<TweetResponse>
}