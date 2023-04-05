package com.study.spotilist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.spotilist.model.Playlist
import com.study.spotilist.repository.PlaylistRepository
import kotlinx.coroutines.launch

class PlaylistViewModel(private val repository: PlaylistRepository) : ViewModel() {

    val playlists = MutableLiveData<List<Playlist>>()

    fun fetchPlaylists() {
        viewModelScope.launch {
            val result = repository.fetchAllPlaylist()
            playlists.value = result
        }
    }
}