package com.example.cookapplite.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cookapplite.R
import com.example.cookapplite.viewmodels.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private lateinit var v : View
    private lateinit var viewModel: LoginViewModel
    private lateinit var emailEditText : EditText
    private lateinit var passEditText : EditText
    private lateinit var passMessage : TextView
    private lateinit var userMessage : TextView
    private lateinit var titleMessage : TextView
    private lateinit var loginBtn : Button
    private lateinit var auth : FirebaseAuth

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)
        passEditText = v.findViewById(R.id.passEditText)
        emailEditText = v.findViewById(R.id.emailEditText)
        passMessage = v.findViewById(R.id.passMessage)
        userMessage = v.findViewById(R.id.userMessage)
        titleMessage = v.findViewById(R.id.titleMessage)
        loginBtn = v.findViewById(R.id.loginBtn)
        return v
    }

    override fun onStart() {
        super.onStart()
        var state = true
        val action2 = LoginFragmentDirections.actionLoginFragmentToAppActivity()

        loginBtn.setOnClickListener {
            if(state){
                if(emailEditText.text.isEmpty()){
                    emailEditText.error = "Debe ingresar un email"
                }
                else{
                    emailEditText.visibility = View.INVISIBLE
                    passEditText.visibility = View.VISIBLE
                    userMessage.visibility = View.INVISIBLE
                    passMessage.visibility = View.VISIBLE
                    titleMessage.text = "Escribir contraseña"
                    state = !state
                }
            }
            else{
                if(passEditText.text.isEmpty()){
                    passEditText.error = "Debe ingresar un email"
                }
                else{
                    viewModel.email.value = emailEditText.text.toString()
                    viewModel.pass.value = passEditText.text.toString()
                    viewModel.authenticateUser()
                }
            }
        }

        userMessage.setOnClickListener {
            //Navegar al fragment add user
            val action1 = LoginFragmentDirections.actionLoginFragmentToAddUserFragment()
            v.findNavController().navigate(action1)
        }

        viewModel.userAuth.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                true -> v.findNavController().navigate(action2)
                else -> Toast.makeText(requireContext(),"Error al logear", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}