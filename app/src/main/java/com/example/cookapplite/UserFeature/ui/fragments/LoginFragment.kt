package com.example.cookapplite.UserFeature.ui.fragments


import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.cookapplite.R
import com.example.cookapplite.databinding.LoginFragmentBinding
import com.example.cookapplite.UserFeature.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    var state = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setObservers()

        binding.loginBtn.setOnClickListener {
            loginLogic()
        }
        binding.userMessage.setOnClickListener {
            viewModel.GoToAddUser()
        }
        binding.emailMessage.setOnClickListener {
            emailRequest()
        }
        binding.passMessage.setOnClickListener {
            showDialog()
        }

    }

    private fun showDialog(){
        val alertDialog : AlertDialog.Builder? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        viewModel.recoveryEmail()
                    })
            }
        }
        alertDialog?.setTitle("Recuperar contraseña")
        alertDialog?.setMessage("Se enviará un mail de recuperación al mail "
                + binding.emailEditText.text.toString())
        alertDialog?.create()
        alertDialog?.show()
    }

    private fun loginLogic(){
        if(state){
            if(binding.emailEditText.text.isEmpty()) binding.emailEditText.error = "Debe ingresar un email"
            else{
                viewModel.email.value = binding.emailEditText.text.toString()
                viewModel.checkEmail()
            }
        }
        else{
            if(binding.passEditText.text.isEmpty()) binding.passEditText.error = "Debe ingresar una contraseña"
            else{
                viewModel.pass.value = binding.passEditText.text.toString()
                viewModel.authenticateUser()
            }
        }
    }

    private fun setObservers(){
        viewModel.authError.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                true -> binding.passEditText.error = "Contraseña incorrecta"
                else -> viewModel.GoToRecipeList()
            }
        })
        viewModel.emailError.observe(viewLifecycleOwner,Observer{ result ->
            when(result){
                0 -> binding.emailEditText.error = "Debe ingresar un email"
                1 -> passRequest()
                2 -> binding.emailEditText.error = "El email no se encuentra registrado"
            }
        })
        viewModel.navigate.observe(viewLifecycleOwner, Observer { result->
            when (result){
                1 -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToAddUserFragment()
                    binding.root.findNavController().navigate(action)
                }
                2 -> {
                    val action = LoginFragmentDirections.actionLoginFragmentToMainActivity()
                    binding.root.findNavController().navigate(action)
                }
            }
        })
    }

    private fun emailRequest(){
        binding.emailEditText.visibility = View.VISIBLE
        binding.passEditText.visibility = View.INVISIBLE
        binding.userMessage.visibility = View.VISIBLE
        binding.passMessage.visibility = View.INVISIBLE
        binding.emailMessage.visibility = View.INVISIBLE

        binding.titleMessage.text = "Iniciar sesión"
        state = !state
    }

    private fun passRequest(){
        binding.emailEditText.visibility = View.INVISIBLE
        binding.passEditText.visibility = View.VISIBLE
        binding.userMessage.visibility = View.INVISIBLE
        binding.passMessage.visibility = View.VISIBLE
        binding.emailMessage.visibility = View.VISIBLE
        binding.emailMessage.text = binding.emailEditText.text

        binding.titleMessage.text = "Escribir contraseña"
        state = !state
    }
}