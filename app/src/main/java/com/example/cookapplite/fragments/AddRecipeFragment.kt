package com.example.cookapplite.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cookapplite.R
import com.example.cookapplite.databinding.AddRecipeFragmentBinding
import com.example.cookapplite.databinding.AddUserFragmentBinding
import com.example.cookapplite.viewmodels.AddRecipeViewModel

class AddRecipeFragment : Fragment() {

    private lateinit var viewModel: AddRecipeViewModel

    private lateinit var _binding : AddRecipeFragmentBinding
    private val binding get() = _binding!!

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

        binding.pickImageBtn.setOnClickListener {
            //Gallery intent
            val gallery = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

        binding.saveInStorageBtn.setOnClickListener{
            //Storage image in database
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data : Intent? = result.data
            binding.imageView.setImageURI(data?.data)
        }
    }
}