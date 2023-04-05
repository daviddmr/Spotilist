package com.study.spotilist.service

import com.study.spotilist.converter.PlaylistConverter
import com.study.spotilist.model.Playlist
import com.study.spotilist.network.Api

class PlaylistService(
    private val api: Api,
    private val converter: PlaylistConverter
) {

    suspend fun fetchAllPlaylist(): List<Playlist> {
        val result = api.fetchAllPlaylists()
        return converter(result)
    }
}