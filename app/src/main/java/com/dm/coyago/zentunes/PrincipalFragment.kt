package com.dm.coyago.zentunes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dm.coyago.zentunes.databinding.FragmentPrincipalBinding
import com.dm.coyago.zentunes.databinding.FragmentSignUpBinding
import com.dm.coyago.zentunes.ui.fragments.MusicListFragment
import com.dm.coyago.zentunes.ui.fragments.MusicRecomendationListFragment


class PrincipalFragment : Fragment() {
    lateinit var  binding: FragmentPrincipalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=
            FragmentPrincipalBinding.bind( inflater.inflate(R.layout.fragment_principal, container, false))
        initControls()
        return binding.root

    }

    private fun initControls() {

        val musiclistFragment = MusicListFragment()
        val musicRecomendationListFragment = MusicRecomendationListFragment()
        val transaction = childFragmentManager.beginTransaction()


        binding.bottomNavigation.setOnItemSelectedListener(){ item ->
            when(item.itemId) {
                R.id.item_music -> {
                    // Respond to navigation item 1 click
                    val transaction = childFragmentManager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, musiclistFragment)
                    transaction.commit()
                    true
                }
                R.id.item_recom -> {
                    // Respond to navigation item 2 click
                    val transaction = childFragmentManager.beginTransaction()
                    transaction.replace(binding.frmContainer.id, musicRecomendationListFragment)
                    transaction.commit()
                    true
                }
                else -> {
                  false

                }
            }
        }


    }

}