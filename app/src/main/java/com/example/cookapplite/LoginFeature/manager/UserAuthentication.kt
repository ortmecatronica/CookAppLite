package com.example.cookapplite.LoginFeature.manager

interface UserAuthentication {
    suspend fun login(email : String, pass : String) : Boolean
    suspend fun createUser(email: String, pass: String) : Boolean
    suspend fun sendRecoveryEmail(email : String)
    suspend fun checkEmail(email : String)
}