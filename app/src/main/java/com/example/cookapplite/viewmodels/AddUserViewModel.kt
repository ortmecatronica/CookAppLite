package com.example.cookapplite.viewmodels

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddUserViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private  var auth = Firebase.auth

    val email = MutableLiveData<String>()
    val pass = MutableLiveData<String>()
    val phone = MutableLiveData<String>()
    val birthday = MutableLiveData<String>()
    val navigate = MutableLiveData<Boolean>()

    fun createUser() {
        auth.createUserWithEmailAndPassword(email.value.toString(), pass.value.toString())
        navigate.value = true
    }

}