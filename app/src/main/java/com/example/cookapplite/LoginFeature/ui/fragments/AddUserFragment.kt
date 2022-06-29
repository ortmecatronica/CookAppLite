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
            val newUser =
                User(null,
                    null,
                    binding.newEmailEditText.toString(),
                    binding.newPhoneEditText.toString(),
                    binding.newBirthDayEditText.toString()
                )
            viewModel.createNewUser(newUser, binding.newPassEditText.toString())
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

