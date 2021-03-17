package com.example.retrofit_room_movies.configuration_files.objects

import android.content.Context
import androidx.room.Room
import com.example.retrofit_room_movies.configuration_files.database.MovieAppDatabase

object RoomClient{
    private var appDatabase: MovieAppDatabase? = null
    fun getInstance(context: Context): MovieAppDatabase{
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(
                context,
                MovieAppDatabase::class.java,
                "appDatabase.db"
            )
                .build()
        }
        return appDatabase!!
    }
}