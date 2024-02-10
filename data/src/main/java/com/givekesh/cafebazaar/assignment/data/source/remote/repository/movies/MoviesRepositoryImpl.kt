package com.givekesh.cafebazaar.assignment.data.source.remote.repository.movies

import com.givekesh.cafebazaar.assignment.data.entity.movie.response.MoviesResponse
import com.givekesh.cafebazaar.assignment.data.source.remote.api.MoviesApi
import javax.inject.Inject

internal class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
) : MoviesRepository {
    override suspend fun getUpcomingMovies(
        page: Int
    ): MoviesResponse = moviesApi.getUpcomingMovies(page = page)
}