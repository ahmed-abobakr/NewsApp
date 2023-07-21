package com.ahmed.abobakr.newsapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {


    val uiState = MutableLiveData<UiState>()
    //val state: LiveData<UiState> = uiState
}