package com.example.cookapplite.LoginFeature.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.LoginFeature.usecases.CreateUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    val createUser : CreateUser
) : ViewModel() {

    private val _create = MutableLiveData<Boolean?>()
    val create : LiveData<Boolean?> get() = _create


    fun createNewUser(newUser: User, newPass : String){

        viewModelScope.launch {
            _create.value = createUser(newUser, newPass)
        }
    }

}