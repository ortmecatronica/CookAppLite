package com.example.cookapplite.LoginFeature.framework

import android.util.Log
import com.example.cookapplite.LoginFeature.manager.UserAuthentication
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class UserAuthenticationImpl () : UserAuthentication {

    private  var auth = Firebase.auth

    override suspend fun login(email: String, pass: String) {
        try {
            auth
                .signInWithEmailAndPassword(email,pass)
                .await()
        }
        catch (e : Exception){
            Log.e("LoginUserViewModel", "Exception caught: ${e.message}")
        }
    }

    override suspend fun createUser(email: String, pass: String) {
        try{
            auth
                .createUserWithEmailAndPassword(email, pass)
                .await()
        }catch (e : Exception){
            Log.e("AddUserViewModel", "Exception caught: ${e.message}")
        }
    }

    override suspend fun sendRecoveryEmail(email: String) {
        try {
            auth
                .sendPasswordResetEmail(email)
                .await()
        }
        catch (e :Exception){
        }
    }

    override suspend fun checkEmail(email: String) {
        try {
            auth
                .fetchSignInMethodsForEmail(email)
                .await()
        }
        catch (e :Exception){
            Log.e("LoginUserViewModel", "Exception caught: ${e.message}")
        }
    }

}