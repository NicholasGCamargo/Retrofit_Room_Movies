package com.example.retrofit_room_movies.configuration_files.services

import com.example.retrofit_room_movies.configuration_files.TMDBClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search/movie?api_key=b66ce801f7917b5c4a5fb578f133773d&language=en-US&page=1&include_adult=false&")
    fun searchMoviesName(@Query("query") name: String): Call<TMDBClass>
}