package com.example.cookapplite.UserFeature.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.cookapplite.databinding.AddUserFragmentBinding
import com.example.cookapplite.UserFeature.ui.viewmodel.AddUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : Fragment() {

    companion object {
        fun newInstance() = AddUserFragment()
    }

    private lateinit var binding : AddUserFragmentBinding
    private lateinit var viewModel: AddUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddUserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.createUserBtn.setOnClickListener {
            if (binding.newEmailEditText.text!!.isEmpty()){
                binding.newEmailEditText.error = "Debe ingrear un email"
            }
            else if(binding.newPassEditText.text!!.isEmpty()){
                binding.newPassEditText.error = "Debe ingresar una contraseña"
            }
            else if(binding.newPhoneEditText.text!!.isEmpty()){
                binding.newPhoneEditText.error = "Debe ingresar un teléfono"
            }
            else if(binding.newBirthDayEditText.text!!.isEmpty()){
                binding.newBirthDayEditText.error = "Debe ingresar su fecha de nacimiento"
            }
            else{
                viewModel.email.value = binding.newEmailEditText.text.toString()
                viewModel.pass.value = binding.newPassEditText.text.toString()
                viewModel.phone.value = binding.newPhoneEditText.text.toString()
                viewModel.birthday.value = binding.newBirthDayEditText.text.toString()
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