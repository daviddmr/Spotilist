package com.study.spotilist.network

import com.study.spotilist.network.model.NWPlaylist
import retrofit2.http.GET

interface Api {

    @GET("/playlists")
    suspend fun fetchAllPlaylists(): List<NWPlaylist>
}