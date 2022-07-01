package com.example.cookapplite.LoginFeature.framework

import android.net.Uri
import com.example.cookapplite.LoginFeature.data.UserStorageData
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import javax.inject.Inject

class UserStorageDataImpl @Inject constructor() : UserStorageData {

    private val storage = Firebase.storage

    override suspend fun addProfileImage(image: Uri?, uid : String) {
        val ProfileImages = storage.reference.child("ProfileImages/" + uid)
        ProfileImages.putFile(image as Uri)
    }

}