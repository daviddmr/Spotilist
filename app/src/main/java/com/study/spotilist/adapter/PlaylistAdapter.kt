package com.study.spotilist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.study.spotilist.R
import com.study.spotilist.databinding.ItemPlaylistBinding
import com.study.spotilist.model.Playlist

class PlaylistAdapter(
    private val list: List<Playlist>,
    private val listener: OnClickListener
) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onItemClicked(item: Playlist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            image.setImageResource(item.image ?: R.mipmap.ic_launcher)
            id.text = item.id
            name.text = item.name
            category.text = item.category
            itemView.setOnClickListener { listener.onItemClicked(item) }
        }
    }

    inner class ViewHolder(binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.itemPlaylistImage
        val id = binding.itemPlaylistId
        val name = binding.itemPlaylistName
        val category = binding.itemPlaylistCategory
    }
}