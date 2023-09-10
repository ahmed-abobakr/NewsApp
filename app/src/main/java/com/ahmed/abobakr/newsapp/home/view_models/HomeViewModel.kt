package com.ahmed.abobakr.newsapp.home.view_models

import androidx.lifecycle.viewModelScope
import com.ahmed.abobakr.newsapp.base.BaseViewModel
import com.ahmed.abobakr.newsapp.base.UiState
import com.ahmed.abobakr.newsapp.home.data.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): BaseViewModel() {

    fun getHeaderTopNews(page: Int){
        uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.getHeaderTopNews(page)
                .catch {
                    uiState.value = UiState.Error(it.message ?: "")
                }
                .collect {
                uiState.value = HomeUiState.Success(it.articles)
            }
        }
    }
}