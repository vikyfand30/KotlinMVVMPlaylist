package com.example.femaledailytest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.femaledailytest.ExceptionWrapper
import com.example.femaledailytest.MainState
import com.example.femaledailytest.repository.Repository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: Repository by lazy {
        Repository(application)
    }

    private var _state = MutableLiveData<MainState>()
    fun state(): LiveData<MainState> = _state

    init {
        loadMusic()

    }

    fun loadMusic() {
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                val trackList = repository.getMusics()
                _state.value = MainState.Success(trackList = trackList)
            } catch (exc: Exception) {
                when (exc) {
                    is ExceptionWrapper.NoNetworkException -> _state.value = MainState.NoNetwork
                    else -> _state.value = MainState.Failed(exc.message.orEmpty())
                }
            }
        }
    }

    fun searchMusicByArtist(artistName: String) {
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                val trackList = repository.getMusicByArtistName(artistName)
                _state.value = MainState.Success(trackList = trackList)
            } catch (exc: Exception) {
                when (exc) {
                    is ExceptionWrapper.NoNetworkException -> _state.value = MainState.NoNetwork
                    else -> _state.value = MainState.Failed(exc.message.orEmpty())
                }
            }
        }
    }

}