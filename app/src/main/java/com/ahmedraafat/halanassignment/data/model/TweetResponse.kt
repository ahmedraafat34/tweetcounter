package com.ahmedraafat.halanassignment.data.model
import com.google.gson.annotations.SerializedName


data class TweetResponse(
    @SerializedName("data")
    var data : Data
) {
    data class Data(
        @SerializedName("id")
        var id: String,
        @SerializedName("text")
        var text: String
    )
}