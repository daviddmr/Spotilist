package com.study.spotilist.converter

import com.study.spotilist.model.Playlist
import com.study.spotilist.network.model.NWPlaylist
import javax.inject.Inject

class PlaylistConverter @Inject constructor() : Function1<List<NWPlaylist>, List<Playlist>> {
    override fun invoke(nwPlaylistList: List<NWPlaylist>): List<Playlist> {
        return nwPlaylistList.map {
            Playlist(
                it.id,
                it.name,
                it.category,
                0
            )
        }
    }
}