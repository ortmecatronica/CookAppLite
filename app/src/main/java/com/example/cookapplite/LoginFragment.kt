package com.example.cookapplite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var v : View
    private lateinit var viewModel: LoginViewModel
    private lateinit var editTextLogin : TextInputEditText
    private lateinit var editTextLayout : TextInputLayout
    private lateinit var loginBtn : Button

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)

        editTextLogin = v.findViewById(R.id.editTextLogin)
        loginBtn = v.findViewById(R.id.loginBtn)
        editTextLayout = v.findViewById(R.id.editTextLayout)

        return v
    }

    override fun onStart() {
        super.onStart()

        var state = 0


        loginBtn.setOnClickListener {
            if(state == 0){
                editTextLogin.setText("")
                editTextLogin.clearFocus()
                editTextLayout.hint = "pass"
                state = 1
            }
            else{
                editTextLogin.setText("")
                editTextLogin.clearFocus()
                //navegación
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}