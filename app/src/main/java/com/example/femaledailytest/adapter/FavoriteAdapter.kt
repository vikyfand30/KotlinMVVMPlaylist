package com.example.femaledailytest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.femaledailytest.R
import com.example.femaledailytest.database.Playlist
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class FavoriteAdapter(
    private val listItems: ArrayList<Playlist>,
    private val listener: FavoriteListener
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = listItems[position]
        holder.artistName.text = item.artist
        holder.artistSong.text = item.song
        holder.btnRemove.text = "Remove"
        holder.btnRemove.setOnClickListener {
            listener.OnItemClicked(item)
        }
    }

    class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var artistName = itemView.findViewById<MaterialTextView>(R.id.tv_artist_name)
        var artistSong = itemView.findViewById<MaterialTextView>(R.id.tv_artist_name)
        var btnRemove = itemView.findViewById<MaterialButton>(R.id.btn_favorite)
    }

    interface FavoriteListener{
        fun OnItemClicked(playlist: Playlist)
    }
}