package com.example.femaledailytest.adapter

import TrackList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.femaledailytest.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import java.util.*
import kotlin.collections.ArrayList

class MusicAdapter(
    private var trackList: MutableList<TrackList>,
    private var listener : MusicAdapterListener
) : RecyclerView.Adapter<MusicAdapter.MyViewHolder>(){

    private var tracks = trackList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    init {
        tracks = trackList
    }

    fun updateTracks(receivedTracks: List<TrackList>){
        tracks.clear()
        tracks.addAll(receivedTracks)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = trackList[position].track
        holder.name?.text = data.artistName
        holder.song?.text = data.trackName
        holder.btnFavorit?.setOnClickListener {
            listener.OnItemClicked(trackList[position])
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: MaterialTextView? = null
        var song: MaterialTextView? = null
        var btnFavorit: MaterialButton? = null

        init {
            name = view.findViewById(R.id.tv_artist_name)
            song = view.findViewById(R.id.tv_song_name)
            btnFavorit = view.findViewById(R.id.btn_favorite)
        }
    }

    interface MusicAdapterListener{
        fun OnItemClicked(trackList: TrackList)
    }
}