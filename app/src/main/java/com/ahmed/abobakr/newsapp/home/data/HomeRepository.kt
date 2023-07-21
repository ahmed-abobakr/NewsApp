package com.ahmed.abobakr.newsapp.home.data

import com.ahmed.abobakr.newsapp.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApi): BaseRepository() {

    suspend fun getHeaderTopNews(page: Int): Flow<TopHeaderNewsResponse>{
        return  flow {
            emit(api.getTopHeadLines(page = page))
        }.catch {
            it.printStackTrace()
            handleException(it)
        }
    }
}