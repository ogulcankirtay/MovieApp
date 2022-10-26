package com.ogulcankirtay.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ogulcankirtay.movieapp.di.dao.GenreRepository
import com.ogulcankirtay.movieapp.di.retrofit.RetrofitRepository
import com.ogulcankirtay.movieapp.models.Genre
import com.ogulcankirtay.movieapp.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val repository: RetrofitRepository) :
    ViewModel(){

    var popularMovieList: MutableLiveData<Movie>
    var recentMovieList: MutableLiveData<Movie>
    var genreList: MutableLiveData<Genre>


    init {
        popularMovieList = MutableLiveData()
        recentMovieList = MutableLiveData()
        genreList = MutableLiveData()
    }

    fun getObserveGenre(): MutableLiveData<Genre>{
        return genreList
    }

    fun getObserverLiveData(isPopular : Boolean) : MutableLiveData<Movie>{
        if(isPopular){
            return popularMovieList
        }else{
            return  recentMovieList
        }
    }

    fun loadGenreData(){
        repository.getAllGenres(genreList)
    }

    fun loadData(page:String, isPopular:Boolean){
        if(isPopular){
            repository.getPopularMovies(page,popularMovieList)
        }else{
            repository.getRecentMovies(page,recentMovieList)
        }
    }


}