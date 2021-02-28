package com.example.retrofit_room_movies.ui.infos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit_room_movies.R

class apiInfos : Fragment() {

    companion object {
        fun newInstance() = apiInfos()
    }

    private lateinit var viewModel: ApiInfosViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.api_infos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ApiInfosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}