package com.example.cookapplite.LoginFeature.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setObservers()

        binding.signUpBtn.setOnClickListener {
            viewModel.createNewUser(binding.newEmailEditText.text.toString(), binding.newPassEditText.text.toString())
        }

    }

    private fun setObservers(){

    }
}

