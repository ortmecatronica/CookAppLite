package com.example.cookapplite.LoginFeature.data

interface UserAuthentication {
    suspend fun login(email : String, pass : String) : Boolean
    suspend fun createUser(email: String, pass: String) : String?
    suspend fun sendRecoveryEmail(email : String)
    suspend fun checkEmail(email : String)
}