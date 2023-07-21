package com.ahmed.abobakr.newsapp.home.view_models

import com.ahmed.abobakr.newsapp.base.UiState
import com.ahmed.abobakr.newsapp.home.data.Article

sealed class HomeUiState: UiState() {

    data class Success(val result: List<Article>) :
        UiState()
}