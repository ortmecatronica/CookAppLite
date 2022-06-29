package com.example.cookapplite.LoginFeature.usecases

import com.example.cookapplite.LoginFeature.data.UsersRepository
import com.example.cookapplite.LoginFeature.domain.User

class CreateUser(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(newUser: User,pass : String) = usersRepository.createUser(newUser,pass)
}