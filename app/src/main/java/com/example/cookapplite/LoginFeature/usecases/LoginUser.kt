package com.example.cookapplite.LoginFeature.usecases

import com.example.cookapplite.LoginFeature.data.UsersRepository

class LoginUser (private val usersRepository: UsersRepository){
suspend operator fun invoke(email : String,pass : String) = usersRepository.loginUser(email,pass)
}