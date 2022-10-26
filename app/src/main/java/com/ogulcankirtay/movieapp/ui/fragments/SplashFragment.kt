package com.ogulcankirtay.movieapp.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ogulcankirtay.movieapp.R
import com.ogulcankirtay.movieapp.databinding.FragmentSplashBinding
import com.ogulcankirtay.movieapp.di.dao.GenreData
import com.ogulcankirtay.movieapp.viewmodel.GenreViewModel
import com.ogulcankirtay.movieapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment: Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var genreViewModel: GenreViewModel
    private lateinit var homePageViewModel: HomePageViewModel
    private var genreList : MutableList<GenreData>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root

        //Genreler Ekleniyor
        genreList = mutableListOf()

        genreViewModel = ViewModelProvider(this).get(GenreViewModel::class.java)

        homePageViewModel = ViewModelProvider(this).get(HomePageViewModel::class.java)

        homePageViewModel.getObserveGenre().observe(viewLifecycleOwner,{
            if(it != null){
                for(item in it.genres){
                    val tr_name = "a"
                    val genre = GenreData(0, item.id, item.name, tr_name)
                    genreList!!.add(genre)
                }
                genreViewModel.addAllGenres(genreList!!)
            }
        })
        homePageViewModel.loadGenreData()
        Handler(Looper.getMainLooper()).postDelayed({
                                                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        },3000)

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}