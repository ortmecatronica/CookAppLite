package com.example.cookapplite.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var auth = Firebase.auth
    val email = MutableLiveData<String>()
    val pass = MutableLiveData<String>()
    val userAuth = MutableLiveData<Boolean>()

    fun authenticateUser(){
        viewModelScope.launch {
            val result = userAuthentication(email.value.toString(),pass.value.toString())
            if (result != null){
                userAuth.value = true
            }
            else{
                userAuth.value = false
            }
        }
    }

    suspend fun userAuthentication(email :String,password :String) : AuthResult?{
        try {
            val result = auth
                .signInWithEmailAndPassword(email,password)
                .await()
            return result
        }
        catch (e : Exception){
            Log.e("LoginUserViewModel", "Exception caught: ${e.message}")
            return null
        }
    }

}