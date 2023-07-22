package com.ahmed.abobakr.newsapp.base

abstract class UiState {
    object Loading : UiState()

    data class Error(val message: String) : UiState()
}