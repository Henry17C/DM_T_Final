package com.dm.coyago.zentunes.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.data.core.My_Applycation
import com.dm.coyago.zentunes.databinding.FragmentSignInBinding
import com.dm.coyago.zentunes.databinding.FragmentSignUpBinding
import com.dm.coyago.zentunes.databinding.FragmentStartBinding
import com.dm.coyago.zentunes.logic.local.login.LoginUserCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {

    lateinit var  binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=
            FragmentSignInBinding.bind( inflater.inflate(R.layout.fragment_sign_in, container, false))

        return binding.root    }



    override fun onStart() {
        super.onStart()
        initControls()

    }
    override fun onDestroy() {
        super.onDestroy()
    }

    fun initControls(){
        //boton Atras
        binding.btnArrow.setOnClickListener {

            findNavController().navigate(R.id.action_signInFragment_to_startFragment)

        }

        binding.btnRegistrate.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        //inicio de sesion
        binding.buttonSesion.setOnClickListener {
            var email = binding.etEmail.text.toString()
            var password = binding.etPassword.text.toString()

            lifecycleScope.launch {
                val check = LoginUserCase(My_Applycation.getConnectionDB())?.checkLoginReturn(email, password)

                if (check ?: 0 > 0) {
                    findNavController().navigate(R.id.action_signInFragment_to_principalFragment)
                } else {
                    Snackbar.make(
                        binding.buttonSesion,
                        "Usuario o contraseña incorrecta",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

    }
}


