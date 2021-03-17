package com.example.retrofit_room_movies.configuration_files.data_class

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String? = null,
    val release_date: String,
    val title: String,
)
