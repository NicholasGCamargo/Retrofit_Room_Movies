package com.example.retrofit_room_movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_room_movies.R
import com.example.retrofit_room_movies.holder_classes.Result
import com.squareup.picasso.Picasso

class TMDBAdapter(val response: List<Result>, val ctx: Context): RecyclerView.Adapter<TMDBAdapter.TMDBViewHolder>() {
    class TMDBViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.RcViewSearchTitle)
        val description: TextView = itemView.findViewById(R.id.RcViewSearchDescription)
        val date: TextView = itemView.findViewById(R.id.RcViewSearchDate)
        val movieImg: ImageView = itemView.findViewById(R.id.RcViewSearchImgVw)
    }

    override fun getItemCount(): Int = response.size;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TMDBViewHolder {
        val card = LayoutInflater.from(parent.context)
                .inflate(
                        R.layout.recycler_fragment_movies_api
                ,parent, false
                )

        return TMDBViewHolder(card)
    }

    override fun onBindViewHolder(holder: TMDBViewHolder, position: Int) {
        val data = response[position]
        holder.date.text = String.format(ctx.resources.getString(R.string.default_date),data.release_date)
        holder.title.text = data.original_title
        holder.description.text = data.overview

        Picasso.get().load("https://image.tmdb.org/t/p/original"+data.poster_path).into(holder.movieImg)
    }


}