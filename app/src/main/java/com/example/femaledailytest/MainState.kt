package com.example.femaledailytest

import TrackList

sealed class MainState {
    object Loading : MainState()
    object NoNetwork : MainState()
    data class Failed(val message: String) : MainState()
    data class Success(val trackList: List<TrackList>) : MainState()
}
