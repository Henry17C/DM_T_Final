package com.dm.coyago.zentunes.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.data.core.My_Applycation
import com.dm.coyago.zentunes.data.local.entities.Users
import com.dm.coyago.zentunes.databinding.FragmentSignInBinding
import com.dm.coyago.zentunes.databinding.FragmentSignUpBinding
import com.dm.coyago.zentunes.logic.local.login.LoginUserCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {


    lateinit var  binding: FragmentSignUpBinding

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                binding=
                    FragmentSignUpBinding.bind( inflater.inflate(R.layout.fragment_sign_up, container, false))
                initControls()
                return binding.root   }





    fun initControls() {
        binding.btnArrow.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_startFragment)
        }

        binding.btnRegistrate.setOnClickListener {

            var name=binding.txtName.text.toString();
            var lastname= binding.txtLastName.text.toString();
            var email= binding.txtEmail.text.toString();
            var password= binding.txtPassword.text.toString();

            var user= Users( email,password, name,lastname)
            //LoginUserCase(My_Applycation.getConnectionDB())?.insertSingleUser(user)

            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val result = LoginUserCase(My_Applycation.getConnectionDB())?.insertSingleUser(user)
                    Snackbar.make(binding.root, "Usuario registrado", Snackbar.LENGTH_SHORT).show()


                    findNavController().navigate(R.id.action_signUpFragment_to_principalFragment)
                    // Maneja el resultado de la inserción aquí
                } catch (e: Exception) {
                   Log.d("TAG", "Error al insertar")
                }
            }

        }

        binding.btnIniciar.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

        }

    }

    }