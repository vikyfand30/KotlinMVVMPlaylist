package com.example.femaledailytest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Playlist::class], version = 1, exportSchema = false)
abstract class PlaylistDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: PlaylistDatabase? = null

        fun getDatabase(context: Context): PlaylistDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaylistDatabase::class.java,
                    "playlist_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    abstract fun getPlaylistDao(): PlaylistDao
}