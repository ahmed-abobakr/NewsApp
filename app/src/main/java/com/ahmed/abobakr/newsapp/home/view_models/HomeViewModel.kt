package com.ahmed.abobakr.newsapp.home.view_models

import androidx.lifecycle.viewModelScope
import com.ahmed.abobakr.newsapp.base.BaseViewModel
import com.ahmed.abobakr.newsapp.base.NetowrkException
import com.ahmed.abobakr.newsapp.base.UiState
import com.ahmed.abobakr.newsapp.home.data.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): BaseViewModel() {

    //var topHeaderNewFlow: StateFlow<UiState>? = null

    fun getHeaderTopNews(page: Int){

        //uiState.value = UiState.Loading
        viewModelScope.launch {

            uiState = repository.getHeaderTopNews(page)
                .map {
                    HomeUiState.Success(it.articles) as UiState
                }.catch {
                    emit(UiState.Error(it.message ?: ""))

                }
                .stateIn(
                    scope = viewModelScope,
                    initialValue = UiState.Loading,
                    started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
                )
        }
    }
}