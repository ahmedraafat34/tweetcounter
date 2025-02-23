package com.ahmedraafat.halanassignment.data

class ApiResponse<out T>(
    val status: Status,
    val data: T?,
    var message: String?
) {
    enum class Status {
        SUCCESS,
        LOADING,
        ERROR
    }

    companion object {

        fun <T> success(data: T?): ApiResponse<T> {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String): ApiResponse<T> {
            return ApiResponse(Status.ERROR, null, message)
        }

        fun <T> loading(data: T?): ApiResponse<T> {
            return ApiResponse(Status.LOADING, data, null)
        }
    }
}