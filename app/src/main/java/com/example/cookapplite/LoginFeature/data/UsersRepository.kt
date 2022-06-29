package com.example.cookapplite.LoginFeature.data

import com.example.cookapplite.LoginFeature.manager.UserAuthentication
import com.example.cookapplite.LoginFeature.manager.UserDataSource

class UsersRepository  constructor(
    private val userAuthentication : UserAuthentication,
    private val userDataSource: UserDataSource
)
{
    suspend fun createUser(email : String, pass : String) : Boolean{
        return userAuthentication.createUser(email, pass)
    }
    suspend fun loginUser(email : String, pass : String) : Boolean{
        return userAuthentication.login(email, pass)
    }
}