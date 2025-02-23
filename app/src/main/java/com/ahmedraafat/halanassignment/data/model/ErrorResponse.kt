package com.ahmedraafat.halanassignment.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    var errors: List<Error>
) {
    data class Error(
        @SerializedName("code")
        var code: Int,
        @SerializedName("message")
        var message: String
    )
}