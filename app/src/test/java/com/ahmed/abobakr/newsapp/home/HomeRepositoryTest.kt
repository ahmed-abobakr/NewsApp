package com.ahmed.abobakr.newsapp.home

import com.ahmed.abobakr.newsapp.base.ApiException
import com.ahmed.abobakr.newsapp.base.BaseResponse
import com.ahmed.abobakr.newsapp.home.data.Article
import com.ahmed.abobakr.newsapp.home.data.HomeApi
import com.ahmed.abobakr.newsapp.home.data.HomeRepository
import com.ahmed.abobakr.newsapp.home.data.TopHeaderNewsResponse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class HomeRepositoryTest {

    private lateinit var repository: HomeRepository
    private  var api: HomeApi = mock()

    @Before
    fun setUp(){
        repository = HomeRepository(api)
    }

    @Test
    fun getTopHeaderNewsFromAPI(){
        runBlocking {
            //Arrange
            //Act
            repository.getHeaderTopNews(1).first()
            //Assert
            verify(api, times(1)).getTopHeadLines(page = 1)
        }
    }

    @Test
    fun emitTopHeaderNewsFromAPI(){
        runBlocking {
            //Arrange
            val topHeaderNewsResponse: TopHeaderNewsResponse = mock()
            whenever(api.getTopHeadLines(page = 1)).thenReturn(topHeaderNewsResponse)
            //Act
            //Assert
            assertEquals(topHeaderNewsResponse, repository.getHeaderTopNews(1).first())
        }
    }

    @Test(expected = ApiException::class)
    fun emitTopHeaderNewsErrorWhenReceiveError(){
        runBlocking {
            //Arrange
            val exception = RuntimeException("Error!")
            whenever(api.getTopHeadLines(page = 1)).thenThrow(exception)
            //Act
            repository.getHeaderTopNews(1).first()
            //Assert
        }
    }
}