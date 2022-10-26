package com.ogulcankirtay.movieapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ogulcankirtay.movieapp.R
import com.ogulcankirtay.movieapp.adapter.MovieAdapter
import com.ogulcankirtay.movieapp.adapter.RecentMovieAdapter
import com.ogulcankirtay.movieapp.databinding.FragmentHomeBinding
import com.ogulcankirtay.movieapp.di.dao.GenreData
import com.ogulcankirtay.movieapp.models.Genre
import com.ogulcankirtay.movieapp.models.Movie
import com.ogulcankirtay.movieapp.viewmodel.GenreViewModel
import com.ogulcankirtay.movieapp.viewmodel.HomePageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var genreList: List<GenreData>?=null
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recentMovieAdapter: RecentMovieAdapter

    val viewModel by lazy{
        ViewModelProvider(this,defaultViewModelProviderFactory).get(HomePageViewModel::class.java)
    }
    val genreViewModel by lazy {
        ViewModelProvider(this,defaultViewModelProviderFactory).get(GenreViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        initRecyclerViews()


        viewModel.getObserverLiveData(true).observe(viewLifecycleOwner, object: Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t != null){
                    movieAdapter.setList(t.results,genreList!!)
                }
            }

        })

        viewModel.getObserverLiveData(false).observe(viewLifecycleOwner, object: Observer<Movie> {
            override fun onChanged(t: Movie?) {
                if(t != null){
                    recentMovieAdapter.setLists(t.results,genreList!!)
                }
            }

        })

        genreViewModel.getRecordsObserver().observe(viewLifecycleOwner,object : Observer<List<GenreData>>{
            override fun onChanged(t: List<GenreData>?) {
                if(t != null){
                    genreList = t
                    fetchMovies()
                }
            }

        })


        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerViews(){

        val lmHorizontal=
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val lmVertical= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)





        binding.recyclerView.layoutManager=lmHorizontal
        binding.recentRecyclerView.layoutManager=lmVertical
        movieAdapter= MovieAdapter()
        recentMovieAdapter= RecentMovieAdapter()
        binding. recyclerView.adapter=movieAdapter
        binding.recentRecyclerView.adapter=recentMovieAdapter


    }

    fun fetchMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            val job1: Deferred<Unit> = async{
                viewModel.loadData("1",true)
            }
            val job2: Deferred<Unit> = async{
                viewModel.loadData("1",false)
            }

            job1.await()
            job2.await()
        }
    }
}