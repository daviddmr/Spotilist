package com.study.spotilist.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.study.spotilist.R
import com.study.spotilist.ui.fragment.PlaylistFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openPlaylistScreen(PlaylistFragment.newInstance(), PlaylistFragment.TAG)
    }

    private fun openPlaylistScreen(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, fragment, tag)
            .commit()
    }
}