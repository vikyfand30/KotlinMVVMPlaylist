package com.example.femaledailytest.model

import com.google.gson.annotations.SerializedName

class PrimaryGenres(
    @SerializedName("music_genre_list") val musicGenres: List<MusicGenre> = listOf()
)