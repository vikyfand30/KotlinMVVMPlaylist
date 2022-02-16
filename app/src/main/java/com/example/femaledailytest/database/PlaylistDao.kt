package com.example.femaledailytest.database

import androidx.room.*


@Dao
interface PlaylistDao {

    @Insert
    fun insert(playlist: Playlist)

    @Update
    fun update(playlist: Playlist)

    @Delete
    fun delete(playlist: Playlist)

    @Query("SELECT * FROM playlist")
    fun getAll(): List<Playlist>

    @Query("SELECT * FROM playlist WHERE id = :id")
    fun getById(id: Int): List<Playlist>

    @Query("SELECT * FROM playlist WHERE track_id =:track_id")
    fun getByTrackId(track_id: Int): List<Playlist>
}