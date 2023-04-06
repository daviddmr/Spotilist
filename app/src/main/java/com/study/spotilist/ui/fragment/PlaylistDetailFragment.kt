package com.study.spotilist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.study.spotilist.databinding.FragmentPlaylistDetailBinding

class PlaylistDetailFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}