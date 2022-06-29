package com.example.cookapplite.LoginFeature.framework

import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.LoginFeature.manager.UserDataSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor() : UserDataSource {

    val db = Firebase.firestore

    override suspend fun addUser(newUser: User) : Boolean{
        var result : Boolean
        try {
            db
                .collection("Users")
                .document(newUser.uuid.toString())
                .set(newUser)
            result = true
        }
        catch (e : Exception){
            result = false
        }
        return result
    }

    override suspend fun getUsers(): List<User> {
        return emptyList()
    }

}