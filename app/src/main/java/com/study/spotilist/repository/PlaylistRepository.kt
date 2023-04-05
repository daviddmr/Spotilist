package com.study.spotilist.repository

import com.study.spotilist.model.Playlist
import com.study.spotilist.service.PlaylistService

class PlaylistRepository(private val service: PlaylistService) {

    suspend fun fetchAllPlaylist(): List<Playlist> {
        return service.fetchAllPlaylist()
    }
}