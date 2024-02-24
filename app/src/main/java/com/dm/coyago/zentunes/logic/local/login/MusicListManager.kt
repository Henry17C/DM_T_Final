package com.dm.coyago.zentunes.logic.local.login

import com.dm.coyago.zentunes.logic.local.Song
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MusicListManager {
    private var songsList: MutableList<Song> = mutableListOf()

    suspend fun getSongsList(): List<Song> = withContext(Dispatchers.IO) {
        songsList
    }

    suspend fun setSongsList(songs: List<Song>) = withContext(Dispatchers.IO) {
        songsList.clear()
        songsList.addAll(songs)
    }
}