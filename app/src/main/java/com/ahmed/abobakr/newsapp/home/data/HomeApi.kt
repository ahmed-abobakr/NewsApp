package com.ahmed.abobakr.newsapp.home.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApi {

    @GET("top-headlines")
    suspend fun getTopHeadLines(@Query("country") country: String = "EG", @Query("apiKey") apiKey: String = "", @Query("page") page: Int): TopHeaderNewsResponse
}