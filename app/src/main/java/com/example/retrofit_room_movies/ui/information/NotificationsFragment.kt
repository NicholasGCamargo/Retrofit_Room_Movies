package com.example.retrofit_room_movies.ui.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.retrofit_room_movies.R
import com.example.retrofit_room_movies.configuration_files.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.getParcelable<Result>(R.string.key_movie_informations.toString())
        FInformation_text_view.text = data.toString()
        Picasso.get().load("https://image.tmdb.org/t/p/original"+data!!.poster_path).into(FInformationMoviePoster)
    }
}