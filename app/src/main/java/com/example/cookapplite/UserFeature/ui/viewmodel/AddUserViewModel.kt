package com.example.cookapplite.UserFeature.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
) : ViewModel() {

    private  var auth = Firebase.auth

    val email = MutableLiveData<String>()
    val pass = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()
    val signUp = MutableLiveData<Boolean>()

    fun createUser() {
        viewModelScope.launch {
            val result = signUpUser(email.value.toString(), pass.value.toString())
            signUp.value = result != null
        }
    }

    suspend fun signUpUser(email:String,password:String) : AuthResult?{
        try{
            val result = auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            return result
        }catch (e : Exception){
            Log.e("AddUserViewModel", "Exception caught: ${e.message}")
            return null
        }
    }

}