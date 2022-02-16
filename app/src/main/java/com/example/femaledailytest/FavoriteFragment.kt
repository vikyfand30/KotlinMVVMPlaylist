package com.example.femaledailytest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.femaledailytest.adapter.FavoriteAdapter
import com.example.femaledailytest.database.Playlist
import com.example.femaledailytest.database.PlaylistDao
import com.example.femaledailytest.database.PlaylistDatabase
import com.example.femaledailytest.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var dao: PlaylistDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPlaylistData()
    }


    private fun getPlaylistData() {
        val database = PlaylistDatabase.getDatabase(context!!.applicationContext)
        val dao = database.getPlaylistDao()
        val listItems = arrayListOf<Playlist>()
        listItems.addAll(dao.getAll())
        setupRecyclerView(listItems)
        if (listItems.isNotEmpty()) {
            binding.textViewPlaylistEmpty.visibility = View.GONE
        } else {
            binding.textViewPlaylistEmpty.visibility = View.VISIBLE
            binding.swipeRefreshFavorite.visibility = View.GONE
        }
    }

    private fun setupRecyclerView(listItems: ArrayList<Playlist>) {
        binding.recyclerFavorite.apply {
            adapter = FavoriteAdapter(listItems, object : FavoriteAdapter.FavoriteListener {
                override fun OnItemClicked(playlist: Playlist) {
                    deletePlaylist(playlist)
                }
            })

            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        getPlaylistData()
    }

    private fun deletePlaylist(playlist: Playlist) {
        dao.delete(playlist)
        Toast.makeText(context, "Song removed", Toast.LENGTH_SHORT).show()
    }
}


