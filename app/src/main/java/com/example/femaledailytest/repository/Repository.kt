package com.example.femaledailytest.repository

import TrackList
import android.app.Application
import com.example.femaledailytest.ExceptionWrapper
import com.example.femaledailytest.network.ApiClient
import com.example.femaledailytest.utils.Utility.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository(private val application: Application) {

    suspend fun getMusics(): List<TrackList> {
        return withContext(Dispatchers.IO) {
            try {
                if (application.isInternetAvailable()) {
                    val response =
                        ApiClient.apiService.getListSong("4f7549e47cbd524ddda8f7ca760b4277")
                            .execute()
                    when (response.isSuccessful) {
                        true -> response.body()?.message?.body?.trackList
                        false -> listOf()
                    }
                } else {
                    throw ExceptionWrapper.NoNetworkException
                }
            } catch (exc: Exception) {
                throw ExceptionWrapper.RequestException(exc.message.orEmpty())
            } as List<TrackList>
        }
    }

    suspend fun getMusicByArtistName(artistName :String): List<TrackList> {
        return withContext(Dispatchers.IO) {
            try {
                if (application.isInternetAvailable()) {
                    val response =
                        ApiClient.apiService.getSongsByArtistName(
                            "4f7549e47cbd524ddda8f7ca760b4277",
                            artistName
                        )
                            .execute()
                    when (response.isSuccessful) {
                        true -> response.body()?.message?.body?.trackList
                        false -> listOf()
                    }
                } else {
                    throw ExceptionWrapper.NoNetworkException
                }
            } catch (exc: Exception) {
                throw ExceptionWrapper.RequestException(exc.message.orEmpty())
            } as List<TrackList>
        }
    }
}