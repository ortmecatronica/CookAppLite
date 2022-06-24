package com.example.cookapplite.RecipeFeature.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
) : ViewModel() {

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

