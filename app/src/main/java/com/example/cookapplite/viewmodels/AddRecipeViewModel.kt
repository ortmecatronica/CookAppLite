package com.example.cookapplite.viewmodels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AddRecipeViewModel : ViewModel() {

    val image = MutableLiveData<Uri>()
    val resultStorage = MutableLiveData<Boolean>()

    val storage = Firebase.storage
    val storageReference = storage.reference

    fun storageImage() {
        val recipesImagesRef = storageReference.child("recipesImages/image1")
        val uploadTask = recipesImagesRef.putFile(image.value!!)

        uploadTask.addOnFailureListener {
            resultStorage.value = false
        }.addOnSuccessListener { taskSnapshot ->
            resultStorage.value = true
        }
    }


}

