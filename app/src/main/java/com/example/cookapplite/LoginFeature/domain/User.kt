package com.example.cookapplite.LoginFeature.domain

import android.net.Uri
import java.util.*

data class User (
    var uuid : String?,
    var username : String?,
    var email : String?,
    var phone : String?,
    var birthday : String?,
    var profileImage : Uri?
)
{}