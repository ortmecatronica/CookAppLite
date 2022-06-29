package com.example.cookapplite.LoginFeature.usecases

import com.example.cookapplite.LoginFeature.data.UsersRepository

class CreateUser(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(email : String,pass : String) = usersRepository.createUser(email,pass)
}