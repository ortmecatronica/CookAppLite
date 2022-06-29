package com.example.cookapplite.LoginFeature.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.usecases.CreateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    val createUser : CreateUser
) : ViewModel() {

    fun createNewUser(email : String,pass : String){
        viewModelScope.launch {
            val result = createUser(email, pass)
        }
    }

}