package com.example.retrofit_room_movies.configuration_files.objects

import com.example.retrofit_room_movies.configuration_files.services.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var instance: Retrofit? = null
    private val url = "https://api.themoviedb.org/3/"
    private fun getInstance():Retrofit{
        if (instance == null){
            instance = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(
                    GsonConverterFactory.create())
                .build()
        }
        return instance as Retrofit
    }

    fun getMoviesService():RetrofitService{
        return getInstance().create(RetrofitService::class.java)
    }
}