package com.dm.coyago.zentunes.ui.fragments

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.databinding.FragmentStartBinding


class StartFragment : Fragment() {
    private lateinit var binding:FragmentStartBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // binding= StartFragment.inflate(layoutInflater)
        binding=FragmentStartBinding.bind( inflater.inflate(R.layout.fragment_start, container, false))

        binding.btnIniciarSesion.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signInFragment)
        }

        binding.btnRegistrate.setOnClickListener {
           findNavController().navigate(R.id.action_startFragment_to_signUpFragment)
        }

        binding.btnInvitado.setOnClickListener {

            ///POR IMPLEMETAR!!!!
           findNavController().navigate(R.id.action_startFragment_to_principalFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissionLauncher.launch(Manifest.permission.READ_MEDIA_AUDIO)
    }




    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }









}