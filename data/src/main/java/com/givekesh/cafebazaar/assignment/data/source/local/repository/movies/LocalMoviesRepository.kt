package com.givekesh.cafebazaar.assignment.data.source.local.repository.movies

import com.givekesh.cafebazaar.assignment.data.entity.movie.MovieEntity

interface LocalMoviesRepository {
    fun fetchMovies(page: Int): List<MovieEntity>
    fun insertAll(movies: List<MovieEntity>)
}