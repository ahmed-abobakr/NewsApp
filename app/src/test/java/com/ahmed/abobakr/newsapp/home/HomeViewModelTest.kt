package com.ahmed.abobakr.newsapp.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmed.abobakr.newsapp.home.view_models.HomeUiState
import com.ahmed.abobakr.newsapp.base.NetowrkException
import com.ahmed.abobakr.newsapp.base.UiState
import com.ahmed.abobakr.newsapp.home.data.Article
import com.ahmed.abobakr.newsapp.home.data.HomeRepository
import com.ahmed.abobakr.newsapp.home.data.TopHeaderNewsResponse
import com.ahmed.abobakr.newsapp.home.view_models.HomeViewModel
import com.ahmed.abobakr.newsapp.utils.MainCoroutineScopeRule
import com.ahmed.abobakr.newsapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository: HomeRepository = mock()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup(){
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun getHeaderTopNewsFromRepoTest(){
        runBlocking {
            //Arrange
            val viewModel = HomeViewModel(repository)
            viewModel.uiState.getValueForTest()
            //Act
            viewModel.getHeaderTopNews(1)
            //Assert
            verify(repository, times(1)).getHeaderTopNews(1)
        }
    }

    @Test
    fun emitTopNewsFromRepo(){
        runBlocking {
            //Arrange
            val listTopNews: List<Article> = mock()
            val topNewsResponse = TopHeaderNewsResponse(
                status = "Ok",
                totalResults = listTopNews.size,
                articles = listTopNews
            )
            val expected = HomeUiState.Success(listTopNews)
            whenever(repository.getHeaderTopNews(1)).thenReturn(
                flow {
                    emit(topNewsResponse)
                }
            )
            //Act
            viewModel.getHeaderTopNews(1)
            //Assert
            assertEquals(expected, viewModel.uiState.getValueForTest())
        }
    }

    @Test
    fun emitTopNewsErrorWhenReceiveError(){
        runBlocking {
            //Arrange
            val exception = NetowrkException("Please check your Internet connection")
            whenever(repository.getHeaderTopNews(1)).thenReturn(
                flow {
                    throw exception
                }
            )
            //Act
            viewModel.getHeaderTopNews(1)
            //Assert
            assertEquals(UiState.Error(exception.message ?: ""), viewModel.uiState.getValueForTest())
        }
    }

}