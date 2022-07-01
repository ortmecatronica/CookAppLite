package com.example.cookapplite.LoginFeature.data

import com.example.cookapplite.LoginFeature.domain.User

class UsersRepository  constructor(
    private val userAuthentication : UserAuthentication,
    private val userDataSource: UserDataSource,
    private val userStorageData: UserStorageData
)
{
    suspend fun createUser(newUser : User, pass : String) : Boolean{
        val newUid = userAuthentication.createUser(newUser.email.toString(),pass)
        return if (newUid == null){
            false
        } else{
            newUser.uuid = newUid
            userDataSource.addUser(newUser)
            userStorageData.addProfileImage(newUser.profileImage)
            true
        }
    }

    suspend fun loginUser(email : String, pass : String) : Boolean{
        return userAuthentication.login(email, pass)
    }

}