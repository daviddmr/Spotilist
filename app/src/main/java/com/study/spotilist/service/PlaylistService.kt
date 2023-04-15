package com.study.spotilist.service

import com.study.spotilist.converter.PlaylistConverter
import com.study.spotilist.model.Playlist
import com.study.spotilist.network.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistService @Inject constructor(
    private val api: Api,
    private val converter: PlaylistConverter
) {

    suspend fun fetchAllPlaylist(): Flow<List<Playlist>> {
        return flow {
            val result = api.fetchAllPlaylists()
            emit(converter(result))
        }
    }
}