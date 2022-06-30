package com.example.cookapplite.LoginFeature.data

import com.example.cookapplite.LoginFeature.domain.User

interface UserDataSource {
    suspend fun addUser(newUser : User) : Boolean
    suspend fun getUsers() : List<User>
}