package com.study.spotilist.repository

import com.study.spotilist.model.Playlist
import com.study.spotilist.service.PlaylistService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service: PlaylistService
) {

    suspend fun fetchAllPlaylist(): Flow<List<Playlist>> {
        return service.fetchAllPlaylist()
    }
}