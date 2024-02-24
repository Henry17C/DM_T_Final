package com.dm.coyago.zentunes.logic.local

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.dm.coyago.zentunes.logic.local.login.MusicListManager

class MusicPlayerManager private constructor() {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying: Boolean = false
    private var currentSongIndex: Int = -1
    private var currentSongUri: Uri? = null
    companion object {
        @Volatile
        private var instance: MusicPlayerManager? = null

        fun getInstance(): MusicPlayerManager =
            instance ?: synchronized(this) {
                instance ?: MusicPlayerManager().also { instance = it }
            }
    }

    fun playMusic(context: Context, uri: Uri) {

        if (uri != null) {
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
                setDataSource(context, uri)
                prepare()
                start()
            }
            currentSongUri = uri
        } else {
            Log.e("MusicLoading", "Error al cargar Musica")
        }
    }


    fun togglePlayback() {
        if (mediaPlayer != null) {
            if (isPlaying) {
                mediaPlayer?.pause()
            } else {
                mediaPlayer?.start()
            }
            isPlaying = !isPlaying
        }
    }

    suspend fun playNext(context: Context): Song {
        var song = Song(
            id = 1,
            title = "Canción de prueba",
            artist = "Artista de prueba",
            album = "Álbum de prueba",
            duration = 180000, // Duración en milisegundos (3 minutos)
            data = "/ruta/de/prueba",
            contentUri = Uri.parse("content://media/external/audio/media/1"),
            albumArtUri = Uri.parse("content://media/external/audio/albumart/1")
        )
        val songsList = MusicListManager.getSongsList()
        if (songsList.isNotEmpty()) {
            val currentSongIndex = songsList.indexOfFirst { it.contentUri == currentSongUri }
            val nextIndex = (currentSongIndex + 1) % songsList.size
            val newUri = songsList[nextIndex].contentUri
            playMusic(context, newUri)
            song =songsList[nextIndex]
        }
        return song
    }

    suspend fun  playPrevious(context: Context): Song {
        var song = Song(
            id = 1,
            title = "Canción de prueba",
            artist = "Artista de prueba",
            album = "Álbum de prueba",
            duration = 180000, // Duración en milisegundos (3 minutos)
            data = "/ruta/de/prueba",
            contentUri = Uri.parse("content://media/external/audio/media/1"),
            albumArtUri = Uri.parse("content://media/external/audio/albumart/1")
        )
        val songsList = MusicListManager.getSongsList()
        if (songsList.isNotEmpty()) {
            val currentSongIndex = songsList.indexOfFirst { it.contentUri == currentSongUri }
            val previousIndex = (currentSongIndex - 1 + songsList.size) % songsList.size
            val newUri = songsList[previousIndex].contentUri
            playMusic(context, newUri)
             song = songsList[previousIndex]
        }
        return song
    }




}
