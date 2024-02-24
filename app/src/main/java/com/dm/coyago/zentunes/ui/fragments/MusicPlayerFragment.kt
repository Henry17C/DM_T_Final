package com.dm.coyago.zentunes.ui.fragments

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.databinding.FragmentMusicListBinding
import com.dm.coyago.zentunes.databinding.FragmentMusicPlayerBinding
import com.dm.coyago.zentunes.logic.local.MusicPlayerManager
import com.dm.coyago.zentunes.logic.local.MusicUtils
import com.dm.coyago.zentunes.logic.local.Song
import com.dm.coyago.zentunes.logic.local.login.MusicListManager
import kotlinx.coroutines.launch


class MusicPlayerFragment : Fragment() {
    private lateinit var binding: FragmentMusicPlayerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         binding = FragmentMusicPlayerBinding.bind( inflater.inflate(R.layout.fragment_music_player, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showMusicInfo()

        binding.btnPause.setOnClickListener {
            MusicPlayerManager.getInstance().togglePlayback()
        }
        binding.btnNext.setOnClickListener {
            lifecycleScope.launch {
                var song=MusicPlayerManager.getInstance().playNext(requireContext())
                loadInfo(song)
            }
        }
        binding.btnPrev.setOnClickListener {
            lifecycleScope.launch {
            var song=MusicPlayerManager.getInstance().playPrevious(requireContext())
                loadInfo(song)
        }}


}
 private fun showMusicInfo(){
     // Recuperar el id de la canción del argumento
     val idMusic = arguments?.getInt("idMusic", -1) ?: -1

     if (idMusic != -1) {

         val song = MusicUtils(requireContext()).getSongById(idMusic.toLong())
         val myUri: Uri? = song?.contentUri

         // Mostrar la información en tu interfaz de usuario

         binding.txtSongName.text = song?.title.toString()
         binding.txtArtistName.text=song?.artist.toString()

         try {
             binding.imageCaratula.load(song?.albumArtUri.toString()){
                 crossfade(true)
                 placeholder(R.drawable.image)
                 transformations(CircleCropTransformation())
             }
         } catch (e: Exception) {
             Log.e("ImageLoading", "Error al cargar la imagen: ${e.message}")
         }

     }




 }




    fun loadInfo(song: Song){
        binding.txtSongName.text = song?.title.toString()
        binding.txtArtistName.text=song?.artist.toString()

        try {
            binding.imageCaratula.load(song?.albumArtUri.toString()){
                crossfade(true)
                placeholder(R.drawable.image)
                transformations(CircleCropTransformation())
            }
        } catch (e: Exception) {
            Log.e("ImageLoading", "Error al cargar la imagen: ${e.message}")
        }

    }
}