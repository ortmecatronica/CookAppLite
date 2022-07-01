package com.example.cookapplite.LoginFeature.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.cookapplite.LoginFeature.domain.User
import com.example.cookapplite.databinding.AddUserFragmentBinding
import com.example.cookapplite.LoginFeature.ui.viewmodel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    companion object {
        fun newInstance() = AddUserFragment()
    }

    private val viewModel: AddUserViewModel by viewModels()
    private lateinit var binding : AddUserFragmentBinding
    private var image : Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            image = result.data?.data
            binding.profileImage.setImageURI(result.data?.data)
        }
    }

    override fun onStart() {
        super.onStart()

        setObservers()

        binding.signUpBtn.setOnClickListener {
            val newUser =
                User(null,
                    null,
                    binding.newEmailEditText.text.toString(),
                    binding.newPhoneEditText.text.toString(),
                    binding.newBirthDayEditText.text.toString(),
                    image
                )
            viewModel.createNewUser(newUser, binding.newPassEditText.text.toString())
        }

        binding.pickProfileImageBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            resultLauncher.launch(gallery)
        }

    }

    private fun setObservers(){
        viewModel.create.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                true -> Toast.makeText(requireContext(),"Usuario creado", Toast.LENGTH_SHORT).show()
                false -> Toast.makeText(requireContext(),"Falla al crear usuario", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

