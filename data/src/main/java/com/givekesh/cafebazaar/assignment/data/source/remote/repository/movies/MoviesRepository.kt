package com.givekesh.cafebazaar.assignment.data.source.remote.repository.movies

import com.givekesh.cafebazaar.assignment.data.entity.movie.response.MoviesResponse

interface MoviesRepository {
    suspend fun getUpcomingMovies(page: Int): MoviesResponse
}