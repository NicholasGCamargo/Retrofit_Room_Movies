package com.example.retrofit_room_movies.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_room_movies.R
import com.example.retrofit_room_movies.adapter.TMDBAdapter
import com.example.retrofit_room_movies.holder_classes.TMDBClass
import com.example.retrofit_room_movies.objects.ApiClient
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    private fun closeKeyboard(view: View){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FHomeBtnFind.setOnClickListener{
            val name = FHomeTxtInputMovie.editText?.text.toString()
            if(name == ""){
                Toast.makeText(
                        context,
                        R.string.error_fill_in_movie_name,
                        Toast.LENGTH_LONG
                ).show()
            }else{
                closeKeyboard(FHomeTxtInputMovie)
                ApiClient.getMoviesService().searchMoviesName(name).enqueue(object: Callback<TMDBClass>{
                    override fun onFailure(call: Call<TMDBClass>, t: Throwable) {
                        Toast.makeText(
                                context,
                                R.string.error_api_retrofit,
                                Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<TMDBClass>, response: Response<TMDBClass>) {
                        if(response.code() == 200 || response.body()?.results?.size != null){
                            FHomeRcViewListMovie.adapter = TMDBAdapter(response.body()!!.results, context!!)
                            FHomeRcViewListMovie.layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                            )
                        }
                    }
                })
            }
        }
    }
}