package com.example.retrofit_room_movies.configuration_files.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.retrofit_room_movies.configuration_files.DAO.MovieDao
import com.example.retrofit_room_movies.configuration_files.data_class.Movie

@Database(
    entities = arrayOf(Movie::class),
    version = 1
)

abstract class MovieAppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}