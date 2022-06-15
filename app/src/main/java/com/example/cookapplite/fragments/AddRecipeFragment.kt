package com.example.cookapplite.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cookapplite.R
import com.example.cookapplite.databinding.AddRecipeFragmentBinding
import com.example.cookapplite.databinding.AddUserFragmentBinding
import com.example.cookapplite.viewmodels.AddRecipeViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class AddRecipeFragment : Fragment() {

    private lateinit var viewModel: AddRecipeViewModel

    private lateinit var _binding : AddRecipeFragmentBinding
    private val binding get() = _binding!!

    private var recipeImage :Uri? = null

    companion object {
        fun newInstance() = AddRecipeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddRecipeViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        val storage = Firebase.storage
        val storageReference = storage.reference

        binding.pickImageBtn.setOnClickListener {
            //Gallery intent
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

        binding.saveInStorageBtn.setOnClickListener {
            //Storage image in database
            val recipesImagesRef = storageReference.child("recipesImages/image1")
            val uploadTask = recipesImagesRef.putFile(recipeImage!!)

            uploadTask.addOnFailureListener {
                Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener { taskSnapshot ->
                Toast.makeText(requireContext(),"uploaded",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            recipeImage = result.data?.data
            binding.imageView.setImageURI(recipeImage)
        }
    }
}