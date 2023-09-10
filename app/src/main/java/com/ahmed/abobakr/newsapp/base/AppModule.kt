package com.ahmed.abobakr.newsapp.base

import com.ahmed.abobakr.newsapp.NewsApp
import com.ahmed.abobakr.newsapp.home.data.HomeApi
import com.ahmed.abobakr.newsapp.home.data.mockTopHeaderNewsResponse
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val testInterceptor = MockNetworkInterceptor().mock(
        path = "https://newsapi.org/v2/top-headlines?country=US&apiKey=763951d0808746d4be1209f43f5443d4&page=1",
        body = { Gson().toJson(mockTopHeaderNewsResponse)},
        status = 200,
        delayInMs = 100
    )

    //private val interceptor = if (NewsApp.isTest) testInterceptor else logger

    private val client = OkHttpClient.Builder().addInterceptor(logger).addInterceptor(testInterceptor).build()

    @Provides
    fun provideAPI(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}