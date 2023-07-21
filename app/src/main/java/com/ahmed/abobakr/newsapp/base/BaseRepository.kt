package com.ahmed.abobakr.newsapp.base

import com.google.gson.Gson
import kotlinx.coroutines.TimeoutCancellationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

open class BaseRepository {

    protected fun handleException(throwable: Throwable) {
        when (throwable) {
            is TimeoutCancellationException -> {
                throw NetowrkException("The connection has timed out.")
            }
            is SocketTimeoutException -> {
                throw NetowrkException("Server error, please try again later.")
            }
            is HttpException -> {
                handleNetworkException(throwable, "Something went wrong!")
            }
            else -> {
                throw ApiException("Something went wrong!")
            }
        }
    }

    private fun handleNetworkException(throwable: HttpException, UNKNOWN_ERROR: String) {
        when (throwable.code()) {
            401 -> throw AuthException(getErrorFrom(throwable, UNKNOWN_ERROR))
            else -> throw ApiException(getErrorFrom(throwable, UNKNOWN_ERROR))
        }
    }

    private fun getErrorFrom(throwable: HttpException, UNKNOWN_ERROR: String): String {
        return try {
            Gson().fromJson(
                throwable.response()?.errorBody()?.string(),
                BaseResponse::class.java
            ).message ?: ""
        } catch (exception: Exception) {
            UNKNOWN_ERROR
        }
    }
}