package com.dm.coyago.zentunes.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.data.network.entities.openwhyd.Track
import com.dm.coyago.zentunes.databinding.FragmentMusicListBinding
import com.dm.coyago.zentunes.databinding.FragmentMusicRecomendationListBinding
import com.dm.coyago.zentunes.logic.openwhyd.GetAllMusicRecomendations
import com.dm.coyago.zentunes.ui.adapters.MusicRecomendationAdapterDiffUtil
import com.dm.coyago.zentunes.ui.viewmodels.PrincipalViewModels
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MusicRecomendationListFragment : Fragment() {

    private lateinit var binding: FragmentMusicRecomendationListBinding
    private val adapter= MusicRecomendationAdapterDiffUtil({selectMusic(it)});
    private val viewModel: PrincipalViewModels by viewModels()
    private lateinit var dialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMusicRecomendationListBinding.bind(
            inflater.inflate(R.layout.fragment_music_recomendation_list, container, false)
        )

        // Obtener los datos directamente en el Fragment
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val musicRecomendations = GetAllMusicRecomendations().invoke()
                if (musicRecomendations.isNotEmpty()) {
                    adapter.submitList(musicRecomendations)
                } else {
                    Log.d("TAG", "La lista está vacía")
                }
            } catch (e: Exception) {
                Log.e("TAG", "Error al obtener los datos: ${e.message}")
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
         initRecyclerView()

    }

    private fun initObservers() {
       /* viewModel.listItems.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                adapter.submitList(list)
                Log.d("AAAAAAAAAAA", list[1].name.toString())
                Log.d("AAAAAAAAAA", "Se ejecuta")
            } else {
                Log.d("AAAAAAAAAA", "La lista está vacía")
            }
        }*/
    }

    private fun initRecyclerView() {

        binding.rvMusicRecomentations.adapter=adapter
        binding.rvMusicRecomentations.layoutManager=
      LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
    }

    private fun selectMusic(it: Track) {
        val videoUrl = it.trackUrl

        // Verificar si la URL no es nula y no está vacía
        if (!videoUrl.isNullOrBlank()) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https:$videoUrl"))
            startActivity(Intent.createChooser(intent, "Selecciona una aplicación"))
        } else {
            // Mostrar un Snackbar cuando la URL está vacía o es nula
            Snackbar.make(requireView(), "La URL de video está vacía o es nula", Snackbar.LENGTH_SHORT).show()
        }
    }



}