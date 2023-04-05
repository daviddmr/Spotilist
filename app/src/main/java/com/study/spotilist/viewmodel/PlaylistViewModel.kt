package com.study.spotilist.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel : ViewModel() {

    val title = MutableLiveData<String>()

    init {
        Handler(Looper.getMainLooper()).postDelayed({
            title.value = "Playlist Fragment!!!"
        }, 1000L)
    }
}