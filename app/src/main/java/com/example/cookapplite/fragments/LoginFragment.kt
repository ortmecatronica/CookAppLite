package com.example.cookapplite.fragments


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
import com.example.cookapplite.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var _binding: LoginFragmentBinding
    private val binding get() = _binding!!

    var state = true

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val action2 = LoginFragmentDirections.actionLoginFragmentToMainActivity()

        binding.loginBtn.setOnClickListener {
            if(state){
                if(binding.emailEditText.text.isEmpty()){
                    binding.emailEditText.error = "Debe ingresar un email"
                }
                else{
                    viewModel.email.value = binding.emailEditText.text.toString()
                    viewModel.checkEmail()
                }
            }
            else{
                if(binding.passEditText.text.isEmpty()){
                    binding.passEditText.error = "Debe ingresar una contraseña"
                }
                else{
                    viewModel.pass.value = binding.passEditText.text.toString()
                    viewModel.authenticateUser()
                }
            }
        }

        binding.userMessage.setOnClickListener {
            //Navegar al fragment add user
            val action1 = LoginFragmentDirections.actionLoginFragmentToAddUserFragment()
            binding.root.findNavController().navigate(action1)
        }

        binding.emailMessage.setOnClickListener {
            refreshUI()
        }

        binding.passMessage.setOnClickListener {

            val alertDialog : AlertDialog.Builder? = activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton(
                        R.string.ok,
                        DialogInterface.OnClickListener { dialog, id ->
                            viewModel.recoveryEmail()
                        })
                    setNegativeButton(R.string.cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
            }
            alertDialog?.setTitle("Recuperar contraseña")
            alertDialog?.setMessage("Se enviará un mail de recuperación al mail "
                    + binding.emailEditText.text.toString())
            alertDialog?.create()
            alertDialog?.show()
        }

        viewModel.authError.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                true -> binding.passEditText.error = "Contraseña incorrecta"
                else -> binding.root.findNavController().navigate(action2)
            }
        })
        viewModel.emailError.observe(viewLifecycleOwner,Observer{ result ->
            when(result){
                0 -> binding.emailEditText.error = "Debe ingresar un email"
                1 -> updateUI()
                2 -> binding.emailEditText.error = "El email no se encuentra registrado"
            }
        })
    }

    private fun refreshUI(){
        binding.emailEditText.visibility = View.VISIBLE
        binding.passEditText.visibility = View.INVISIBLE
        binding.userMessage.visibility = View.VISIBLE
        binding.passMessage.visibility = View.INVISIBLE
        binding.emailMessage.visibility = View.INVISIBLE

        binding.titleMessage.text = "Iniciar sesión"
        state = !state
    }
    private fun updateUI(){
        binding.emailEditText.visibility = View.INVISIBLE
        binding.passEditText.visibility = View.VISIBLE
        binding.userMessage.visibility = View.INVISIBLE
        binding.passMessage.visibility = View.VISIBLE
        binding.emailMessage.visibility = View.VISIBLE
        binding.emailMessage.text = binding.emailEditText.text

        binding.titleMessage.text = "Escribir contraseña"
        state = !state
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}