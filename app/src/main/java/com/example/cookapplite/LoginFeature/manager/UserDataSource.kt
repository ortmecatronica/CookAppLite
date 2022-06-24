package com.example.cookapplite.LoginFeature.manager

import com.example.cookapplite.LoginFeature.domain.User

interface UserDataSource {
    suspend fun addUser(user: User)
    suspend fun getUsers() : List<User>
}