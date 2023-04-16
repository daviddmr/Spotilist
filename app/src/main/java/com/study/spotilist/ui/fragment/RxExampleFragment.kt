package com.study.spotilist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.study.spotilist.databinding.FragmentRxExampleBinding
import com.study.spotilist.viewmodel.RxExampleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RxExampleFragment : Fragment() {

    private val viewModel: RxExampleViewModel by viewModels()
    private lateinit var binding: FragmentRxExampleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRxExampleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onRxTest()
    }

}