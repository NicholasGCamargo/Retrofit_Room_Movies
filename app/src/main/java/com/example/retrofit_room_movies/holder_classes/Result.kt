package com.example.retrofit_room_movies.holder_classes

data class Result(
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
)