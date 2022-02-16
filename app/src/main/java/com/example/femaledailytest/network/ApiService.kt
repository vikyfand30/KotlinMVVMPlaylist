package com.example.femaledailytest.network

import ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("ws/1.1/track.search")
    fun getListSong(
        @Query("apikey") apikey: String
    ): Call<ResponseBody>

    @Headers("Content-Type: application/json")
    @GET("ws/1.1/track.search")
    fun getSongsByArtistName(
        @Query("apikey") apikey: String,
        @Query("q_artist") artistName: String
    ): Call<ResponseBody>

}