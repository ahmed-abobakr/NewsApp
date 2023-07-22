package com.ahmed.abobakr.newsapp.home.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HomeApi {

    @GET("top-headlines")
    suspend fun getTopHeadLines(@Query("country") country: String = "US", @Query("apiKey") apiKey: String = "763951d0808746d4be1209f43f5443d4", @Query("page") page: Int): TopHeaderNewsResponse
}