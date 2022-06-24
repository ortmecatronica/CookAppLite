package com.example.cookapplite.LoginFeature.domain

import java.util.*

data class User (
    val uuid : String,
    var username : String,
    var email : String,
    var phone : String,
    var birthday : String,
)
{}