package com.example.femaledailytest

import TrackList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.femaledailytest.adapter.MusicAdapter
import com.example.femaledailytest.database.Playlist
import com.example.femaledailytest.database.PlaylistDao
import com.example.femaledailytest.database.PlaylistDatabase
import com.example.femaledailytest.databinding.FragmentPlaylistBinding
import com.example.femaledailytest.utils.Utility.showErrorToast
import com.example.femaledailytest.viewmodel.MainActivityViewModel


class PlaylistFragment : Fragment() {

    private lateinit var musicAdapter: MusicAdapter
    private lateinit var viewModel: MainActivityViewModel
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private lateinit var binding: FragmentPlaylistBinding


    private lateinit var playlist: Playlist
    private var isUpdate = false
    private lateinit var database: PlaylistDatabase
    private lateinit var dao: PlaylistDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = PlaylistDatabase.getDatabase(context!!.applicationContext)
        dao = database.getPlaylistDao()

        swipeRefreshLayout = binding.swipeRefreshPlaylist
        binding.recyclerMain.setHasFixedSize(true)
        binding.recyclerMain.layoutManager = LinearLayoutManager(context)
        musicAdapter = MusicAdapter(mutableListOf(), object : MusicAdapter.MusicAdapterListener {
            override fun OnItemClicked(trackList: TrackList) {
                val artistName = trackList.track.artistName
                val songName = trackList.track.trackName
                val numFav = trackList.track.numFavourite
                val trackId = trackList.track.trackId

                savePlaylist(
                    Playlist(
                        artist = artistName,
                        song = songName,
                        fav = numFav,
                        track_id = trackId
                    )
                )
            }
        })
        binding.recyclerMain.adapter = musicAdapter
        binding.searchView.isSubmitButtonEnabled = true

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.state().observe(this) { state ->
            when (state) {
                is MainState.Failed -> {
                    swipeRefreshLayout?.isRefreshing = false
                    context!!.showErrorToast(state.message)
                }
                MainState.Loading -> binding.swipeRefreshPlaylist
                    .isRefreshing = true
                MainState.NoNetwork -> {
                    swipeRefreshLayout?.isRefreshing = false
                    context!!.showErrorToast(getString(R.string.no_network))
                }
                is MainState.Success -> {
                    musicAdapter.updateTracks(state.trackList)
                    swipeRefreshLayout?.isRefreshing = false
                }
            }
        }

        performSearch()

        swipeRefreshLayout?.setOnRefreshListener {
            viewModel.loadMusic()
        }
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMusicByArtist(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchMusicByArtist(newText)
                return true
            }
        })
    }


    private fun savePlaylist(playlist: Playlist) {
        if (dao.getById(playlist.id).isEmpty()) {

            dao.insert(playlist)
        } else {

            dao.update(playlist)
        }

        Toast.makeText(context, "Playlist saved", Toast.LENGTH_SHORT).show()

    }


}