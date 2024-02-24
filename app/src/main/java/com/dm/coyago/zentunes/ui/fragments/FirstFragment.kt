package com.dm.coyago.zentunes.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dm.coyago.zentunes.R
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController

class FirstFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)

        val handler = Handler()
        val delayMillis: Long = 5000 // 5 segundos

        handler.postDelayed({
            findNavController().navigate(R.id.action_firstFragment_to_startFragment)
        }, delayMillis)

        return root
    }





    }
