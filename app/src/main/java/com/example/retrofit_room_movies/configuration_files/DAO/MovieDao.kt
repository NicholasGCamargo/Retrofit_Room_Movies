package com.example.retrofit_room_movies.configuration_files.DAO

import androidx.room.*
import com.example.retrofit_room_movies.configuration_files.data_class.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movies: Movie)

    @Delete
    fun delete(vararg movies: Movie)

    @Query("SELECT * FROM Movie")
    fun all(): List<Movie>
}