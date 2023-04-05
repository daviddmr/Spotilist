package com.study.spotilist.repository

import com.study.spotilist.model.Playlist
import com.study.spotilist.service.PlaylistService
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService
) {

    suspend fun fetchAllPlaylist(): List<Playlist> {
        return service.fetchAllPlaylist()
    }
}