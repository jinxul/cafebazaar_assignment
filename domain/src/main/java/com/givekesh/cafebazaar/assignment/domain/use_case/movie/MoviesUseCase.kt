package com.givekesh.cafebazaar.assignment.domain.use_case.movie

import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.util.DataState
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getUpcomingMovies(page: Int): Flow<DataState<List<Movie>>>
    fun getLocalUpcomingMovies(page: Int): Flow<DataState<List<Movie>>>
}