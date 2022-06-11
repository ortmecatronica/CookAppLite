package com.example.cookapplite.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.cookapplite.R
import com.example.cookapplite.databinding.AddUserFragmentBinding
import com.example.cookapplite.databinding.LoginFragmentBinding
import com.example.cookapplite.viewmodels.AddUserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddUserFragment : Fragment() {

    private lateinit var _binding : AddUserFragmentBinding
    private val binding get() = _binding!!

    private lateinit var viewModel: AddUserViewModel

    companion object {
        fun newInstance() = AddUserFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddUserFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        binding.createUserBtn.setOnClickListener {
            if (binding.newEmailEditText.text.isEmpty()){
                binding.newEmailEditText.error = "Debe ingrear un email"
            }
            else if(binding.newPasslEditText .text.isEmpty()){
                binding.newPasslEditText.error = "Debe ingresar una contraseña"
            }
            else if(binding.newPhoneEditText.text.isEmpty()){
                binding.newPhoneEditText.error = "Debe ingresar un teléfono"
            }
            else if(binding.newBirtdayEditText.text.isEmpty()){
                binding.newBirtdayEditText.error = "Debe ingresar su fecha de nacimiento"
            }
            else{
                viewModel.email.value = binding.newEmailEditText.text.toString()
                viewModel.pass.value = binding.newPasslEditText.text.toString()
                viewModel.phone.value = binding.newPhoneEditText.text.toString()
                viewModel.birthday.value = binding.newBirtdayEditText.text.toString()
                viewModel.createUser()
            }
        }

        viewModel.signUp.observe(viewLifecycleOwner, Observer { result ->
            when (result){
                true -> binding.root.findNavController().popBackStack()
                else -> Toast.makeText(requireContext(),"Error al crear el usuario",Toast.LENGTH_SHORT).show()
            }
        })

    }

}