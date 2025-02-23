package com.ahmedraafat.halanassignment.data.repository

import retrofit2.HttpException
import com.ahmedraafat.halanassignment.data.ApiResponse
import com.ahmedraafat.halanassignment.data.apiservice.TwitterApiService
import com.ahmedraafat.halanassignment.data.model.ErrorResponse
import com.ahmedraafat.halanassignment.data.model.TweetRequest
import com.ahmedraafat.halanassignment.data.model.TweetResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class TweetRepository @Inject constructor(
    private val apiService: TwitterApiService
) {
    private var responseCode: Int = 0
    suspend fun postTweet(tweet: String): ApiResponse<TweetResponse> {
        try {
            return withContext(Dispatchers.IO) {
                ApiResponse.loading(null)
                val response = apiService.postTweet(
                   TweetRequest(tweet)
                )
                responseCode = response.code()
                if (response.isSuccessful) {
                    ApiResponse.success(response.body())
                } else {
                    val gson = Gson()
                    val type = object : TypeToken<ErrorResponse>() {}.type
                    val errorResponse: ErrorResponse? =
                        gson.fromJson(response.errorBody()!!.charStream(), type)
                    val message = errorResponse?.errors?.get(0)?.message
                    ApiResponse.error(message.toString())
                }
            }
        } catch (e: HttpException) {
            return ApiResponse.error(e.message().toString())
        } catch (e: IOException) {
            return ApiResponse.error(
                "No Internet"
            )
        }
    }
}