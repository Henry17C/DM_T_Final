package com.dm.coyago.zentunes.ui.fragments

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dm.coyago.zentunes.PrincipalFragment
import com.dm.coyago.zentunes.PrincipalFragmentDirections
import com.dm.coyago.zentunes.R
import com.dm.coyago.zentunes.databinding.FragmentMusicListBinding
import com.dm.coyago.zentunes.logic.local.MusicPlayerManager
import com.dm.coyago.zentunes.logic.local.MusicUtils
import com.dm.coyago.zentunes.logic.local.Song
import com.dm.coyago.zentunes.logic.local.login.MusicListManager
import com.dm.coyago.zentunes.ui.adapters.MusicAdapterDiffUtil
import com.dm.coyago.zentunes.ui.viewmodels.PrincipalViewModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log


class MusicListFragment : Fragment() {

    private lateinit var binding: FragmentMusicListBinding
    private var songsList:MutableList<Song> = ArrayList()
    private var musicDiffAdapter= MusicAdapterDiffUtil({selectMusic(it)})
    private var mediaPlayer: MediaPlayer? = null

    private fun selectMusic(it: Song) {
        /*if (it.id.toInt() != -1) {
            val myUri: Uri? = it.contentUri
            if (myUri != null) {
                // Detener la reproducción anterior si la hay
                mediaPlayer?.stop()
                mediaPlayer?.reset()

                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(requireContext(), myUri)
                    prepare()
                    start()
                }
            } else {
                Log.e("MusicLoading", "Error al cargar Musica")
            }
        }
*/
        MusicPlayerManager.getInstance().playMusic( requireContext(), it.contentUri)
        findNavController().navigate(
            PrincipalFragmentDirections.actionPrincipalFragmentToMusicPlayerFragment(idMusic = it.id.toInt())
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentMusicListBinding.bind( inflater.inflate(R.layout.fragment_music_list, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycleViewDiffUtill()

    }


    fun initRecycleViewDiffUtill() {
        binding.rvSongs.adapter = musicDiffAdapter
        binding.rvSongs.layoutManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        lifecycleScope.launch {
            loadDataRecyclerView()
        }
        var pr = PrincipalViewModels()
        Log.d("MXA", pr.getAllNobelPrizes().javaClass.name.toString())
    }

  /*  private fun loadDataRecyclerView() {
        lifecycleScope.launch(Dispatchers.Main) {
           // binding.progresBar.visibility= View.VISIBLE
            val songs= withContext(Dispatchers.IO){ getSongList()}
           // Log.d("Song Title", getSongList().toString())
            songsList.addAll(getSongList())
            insertSongsDiffUtil(songsList)


        }
    }*/
  private suspend fun loadDataRecyclerView() {
      val songs = withContext(Dispatchers.IO) { getSongList() }
      MusicListManager.setSongsList(songs)
      musicDiffAdapter.submitList(songs)
  }

  suspend fun  getSongList(): List<Song> = MusicUtils(requireContext()).getAllSongs()



    private fun insertSongsDiffUtil(song:List<Song>) {
        songsList.addAll(song)
        musicDiffAdapter.submitList(songsList.toList())

    }


}