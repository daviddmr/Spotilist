package com.study.spotilist.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.study.spotilist.converter.PlaylistConverter
import com.study.spotilist.databinding.FragmentPlaylistBinding
import com.study.spotilist.network.Api
import com.study.spotilist.repository.PlaylistRepository
import com.study.spotilist.service.PlaylistService
import com.study.spotilist.viewmodel.PlaylistViewModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaylistFragment : Fragment() {

    companion object {
        const val TAG = "PlaylistFragment"

        fun newInstance() = PlaylistFragment()
    }

    private val okHttpClient = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://localhost:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(Api::class.java)
    private val converter = PlaylistConverter()
    private val service = PlaylistService(api, converter)
    private val repository = PlaylistRepository(service)

    private lateinit var binding: FragmentPlaylistBinding

    //    private val viewModel: PlaylistViewModel by viewModels()
    private val viewModel by viewModels<PlaylistViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                return when (modelClass) {
                    PlaylistViewModel::class.java -> PlaylistViewModel(repository) as T
                    else -> throw IllegalArgumentException("Unsupported ViewModel type")
                }
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchPlaylists()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.playlists.observe(viewLifecycleOwner) { playlists ->
            Log.d(TAG, "Playlists: $playlists")
        }
    }
}