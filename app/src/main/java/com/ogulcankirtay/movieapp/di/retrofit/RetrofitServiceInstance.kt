package com.ogulcankirtay.movieapp.di.retrofit

import com.ogulcankirtay.movieapp.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceInstance {
    @GET("3/movie/popular?api_key=32ca88addb4f5c1eb60a0f61d1ba1ce6")
    fun getPopularVideos(@Query("page") query: String): Call<Movie>

    @GET("3/movie/now_playing?api_key=32ca88addb4f5c1eb60a0f61d1ba1ce6")
    fun getRecentVideos(@Query("page") query: String): Call<Movie>

    @GET("3/genre/movie/list?api_key=32ca88addb4f5c1eb60a0f61d1ba1ce6")
    fun getGenres(): Call<Genre>

    @GET("3/movie/{id}/videos?api_key=32ca88addb4f5c1eb60a0f61d1ba1ce6")
    fun getTrailerTeasers(@Path("id") id:Int): Call<Trailer>

    @GET("3/search/movie?api_key=32ca88addb4f5c1eb60a0f61d1ba1ce6&language-en-US")
    fun getSuggestions(@Query("query") query: String): Call<Movie>
}