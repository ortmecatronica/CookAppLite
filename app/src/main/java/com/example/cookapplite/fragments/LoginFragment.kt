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

        loginBtn.setOnClickListener {
            if(state){
                if(emailEditText.text.isNotEmpty()) {
                    emailEditText.visibility = View.INVISIBLE
                    passEditText.visibility = View.VISIBLE
                    userMessage.visibility = View.INVISIBLE
                    passMessage.visibility = View.VISIBLE
                    titleMessage.text = "Escribir contraseña"
                    state = !state
                }
                else{
                    emailEditText.error = "Debe ingresar un email"
                }
            }
            else{
                if(passEditText.text.isNotEmpty()){
                    //Firebase Auth
                }
                else{
                    passEditText.error = "Debe ingresar una contraseña"
                }
            }
        }
        userMessage.setOnClickListener {
            //Navegar al fragment add user
            val action = LoginFragmentDirections.actionLoginFragmentToAddUserFragment()
            v.findNavController().navigate(action)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}