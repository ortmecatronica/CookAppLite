package com.example.cookapplite.LoginFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
): ViewModel() {

    private val _navigate = MutableLiveData<Int>()
    val navigate : LiveData<Int> get() = _navigate

    fun GoToAddUser(){
        _navigate.value = 1
    }

    fun GoToRecipeList(){
        _navigate.value = 2
    }

}