package com.example.cookapplite.LoginFeature.data

import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.LoginFeature.manager.UserAuthentication
import com.example.cookapplite.LoginFeature.manager.UserDataSource

class UsersRepository  constructor(
    private val userAuthentication : UserAuthentication,
    private val userDataSource: UserDataSource
)
{
    suspend fun createUser(newUser : User, pass : String) : Boolean{
        val uuid = userAuthentication.createUser(newUser.email.toString(), pass)
        newUser.uuid = uuid
        return if(uuid == null) {
            userDataSource.addUser(newUser)
        } else{
            false
        }
    }
    suspend fun loginUser(email : String, pass : String) : Boolean{
        return userAuthentication.login(email, pass)
    }
    suspend fun saveUserInfo(newUser : User) : Boolean{
        return userDataSource.addUser(newUser)
    }
}