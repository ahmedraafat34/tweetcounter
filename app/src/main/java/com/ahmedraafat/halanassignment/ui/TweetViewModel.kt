package com.ahmedraafat.halanassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedraafat.halanassignment.data.ApiResponse
import com.ahmedraafat.halanassignment.data.model.TweetResponse
import com.ahmedraafat.halanassignment.data.repository.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetViewModel @Inject constructor(
    private val tweetRepository: TweetRepository
) : ViewModel() {
    private val _remainingChars = MutableLiveData(280)
    val remainingChars: LiveData<Int> = _remainingChars

    private val _tweetResponse = MutableLiveData<ApiResponse<TweetResponse>>()
    val tweetResponse: LiveData<ApiResponse<TweetResponse>> = _tweetResponse

    fun updateRemainingCharacters(input: String) {
        _remainingChars.value = 280 - input.length
    }

    fun postTweet(tweetText: String) {
        if (tweetText.isNotBlank() && (_remainingChars.value ?: 0) >= 0) {
            viewModelScope.launch {
                _tweetResponse.value = ApiResponse.loading(null)
                val response = tweetRepository.postTweet(tweetText)
                _tweetResponse.value = response
            }
        } else {
            _tweetResponse.value = ApiResponse.error("Tweet is empty or exceeds character limit!")

        }
    }
}
