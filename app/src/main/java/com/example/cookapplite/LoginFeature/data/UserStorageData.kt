package com.example.cookapplite.LoginFeature.data

import android.net.Uri

interface UserStorageData {

    suspend fun addProfileImage(image : Uri?)

}