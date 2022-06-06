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
import com.example.cookapplite.viewmodels.AddUserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AddUserFragment : Fragment() {

    private lateinit var v : View
    private lateinit var newEmailEditText: EditText
    private lateinit var newPassEditText:EditText
    private lateinit var newBirthdayEditText: EditText
    private lateinit var newPhoneEditText: EditText
    private lateinit var createUserBtn : Button


    companion object {
        fun newInstance() = AddUserFragment()
    }

    private lateinit var viewModel: AddUserViewModel
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.add_user_fragment, container, false)
        newEmailEditText = v.findViewById(R.id.newEmailEditText)
        newPassEditText = v.findViewById(R.id.newPasslEditText)
        newPhoneEditText = v.findViewById(R.id.newPhoneEditText)
        newBirthdayEditText = v.findViewById(R.id.newBirtdayEditText)
        createUserBtn = v.findViewById(R.id.createUserBtn)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()
        auth = Firebase.auth-
        createUserBtn.setOnClickListener {
            if (newEmailEditText.text.isEmpty()){
                newEmailEditText.error = "Debe ingrear un email"
            }
            else if(newPassEditText.text.isEmpty()){
                newPassEditText.error = "Debe ingresar una contraseña"
            }
            else if(newPhoneEditText.text.isEmpty()){
                newPhoneEditText.error = "Debe ingresar un teléfono"
            }
            else if(newBirthdayEditText.text.isEmpty()){
                newBirthdayEditText.error = "Debe ingresar su fecha de nacimiento"
            }
            else{
                auth.createUserWithEmailAndPassword(newEmailEditText.text.toString(), newPassEditText.text.toString())
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(requireContext(),"Usuario creado",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(requireContext(),"No se pudo crear el usuario",Toast.LENGTH_SHORT).show()
                        }
                    }
                /*viewModel.email.value = newEmailEditText.text.toString()
                viewModel.pass.value = newPassEditText.text.toString()
                viewModel.phone.value = newPhoneEditText.text.toString()
                viewModel.birthday.value = newBirthdayEditText.text.toString()
                viewModel.createUser()*/
            }
        }

        viewModel.navigate.observe(viewLifecycleOwner, Observer { result ->
            when (result){
                true -> v.findNavController().popBackStack()
                else -> Toast.makeText(requireContext(),"Error al crear el usuario",Toast.LENGTH_SHORT).show()
            }
        })

    }

}