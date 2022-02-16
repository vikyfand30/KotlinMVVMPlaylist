package com.example.femaledailytest.model

import com.google.gson.annotations.SerializedName

data class MusicGenre(
    @SerializedName("music_genre_id") val genreId: Int?,
    @SerializedName("music_genre_parent_id") val genreParentId: Int?,
    @SerializedName("music_genre_name") val genreName: String?,
    @SerializedName("music_genre_name_extended") val genreNameExtended: String?,
    @SerializedName("music_genre_vanity") val genreVanity: String?,
)
