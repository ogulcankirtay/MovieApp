package com.ogulcankirtay.movieapp.models

import com.ogulcankirtay.movieapp.di.dao.GenreData

data class Movie (
    val page : Int,
    val results : List<Result>,
    val total_pages: Int,
    val total_results:Int
)