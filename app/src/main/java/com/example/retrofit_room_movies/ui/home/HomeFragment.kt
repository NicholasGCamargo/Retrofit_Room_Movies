package com.example.retrofit_room_movies.ui.home

import android.content.Context
import android.content.Intent
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_room_movies.R
import com.example.retrofit_room_movies.adapter.TMDBAdapter
import com.example.retrofit_room_movies.configuration_files.TMDBClass
import com.example.retrofit_room_movies.configuration_files.objects.ApiClient
import com.example.retrofit_room_movies.configuration_files.viewModel.InformationViewModel
import com.example.retrofit_room_movies.ui.information.NotificationsFragment
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var informationViewModel: InformationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        activity?.let {
            informationViewModel = ViewModelProvider(it).get(InformationViewModel::class.java)
        }

        return root
    }

    private fun closeKeyboard(view: View){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(informationViewModel.item != null){
            FHomeRcViewListMovie.adapter = TMDBAdapter(informationViewModel.item!!.results){
                val bundle = Bundle()
                bundle.putParcelable(R.string.key_movie_informations.toString(),it)
                findNavController().navigate(R.id.navigation_notifications, bundle)
            }
            FHomeRcViewListMovie.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
        }

        FHomeBtnFind.setOnClickListener{
            FHomeApiLoading.visibility = View.VISIBLE
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
                        FHomeApiLoading.visibility = View.INVISIBLE
                        Toast.makeText(
                                context,
                                R.string.error_api_retrofit,
                                Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<TMDBClass>, response: Response<TMDBClass>) {
                        FHomeApiLoading.visibility = View.INVISIBLE
                        if(response.code() == 200){
                            FHomeRcViewListMovie.adapter = TMDBAdapter(response.body()!!.results){
                                val bundle = Bundle()
                                bundle.putParcelable(R.string.key_movie_informations.toString(),it)
                                findNavController().navigate(R.id.navigation_notifications, bundle)
                            }
                            FHomeRcViewListMovie.layoutManager = LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                            )
                            informationViewModel.item = response.body()!!
                        }else{
                            Toast.makeText(
                                    context,
                                    R.string.error_response_not_200,
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            }
        }
    }
}