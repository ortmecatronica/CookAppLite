package com.example.cookapplite.UserFeature.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
): ViewModel() {

    private var auth = Firebase.auth
    val email = MutableLiveData<String>()
    val pass = MutableLiveData<String>()
    val authError = MutableLiveData<Boolean>()
    val emailError = MutableLiveData<Int>()

    private val _navigate = MutableLiveData<Int>()
    val navigate : LiveData<Int> get() = _navigate

    fun checkEmail(){
        viewModelScope.launch {
            val result = checkEmailExist(email.value.toString())
            val signInMethods = result?.signInMethods
            if(result == null){
                //El texto ingresado no corresponde a un email
                emailError.value = 0
            }
            else if(signInMethods?.size?.compareTo(1) == 0) {
                //Email registrado
                emailError.value = 1
            }
            else{
                //Email no registrado
                emailError.value = 2
            }

        }
    }

    fun authenticateUser(){
        viewModelScope.launch {
            val result = checkUserAuthentication(email.value.toString(),pass.value.toString())
            authError.value = result == null
        }
    }

    fun recoveryEmail(){
        viewModelScope.launch {
            sendRecoveryEmail()
        }
    }

    suspend fun sendRecoveryEmail(){
        try {
            auth
                .sendPasswordResetEmail(email.value.toString())
                .await()
        }
        catch (e :Exception){

        }
    }

    suspend fun checkEmailExist(email: String):SignInMethodQueryResult?{
        try {
            val result = auth
                .fetchSignInMethodsForEmail(email)
                .await()
            return result
        }
        catch (e :Exception){
            Log.e("LoginUserViewModel", "Exception caught: ${e.message}")
            return null
        }
    }

    suspend fun checkUserAuthentication(email :String,password :String) : AuthResult?{
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
    fun GoToAddUser(){
        _navigate.value = 1
    }
    fun GoToRecipeList(){
        _navigate.value = 2
    }

}