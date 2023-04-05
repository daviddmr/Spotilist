package com.study.spotilist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.study.spotilist.databinding.FragmentPlaylistBinding
import com.study.spotilist.viewmodel.PlaylistViewModel

class PlaylistFragment : Fragment() {

    companion object {
        const val TAG = "PlaylistFragment"

        fun newInstance() = PlaylistFragment()
    }

    private lateinit var binding: FragmentPlaylistBinding
    private val viewModel: PlaylistViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.fragmentPlaylistTitle.text = title
        }
    }
}