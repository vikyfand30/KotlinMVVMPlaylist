package com.example.femaledailytest.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "playlist")

@Parcelize
data class Playlist(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "artist") var artist: String = "",
    @ColumnInfo(name = "song") var song: String = "",
    @ColumnInfo(name = "fav") var fav : Int = 0,
    @ColumnInfo(name = "track_id") var track_id : Int = 0
) : Parcelable