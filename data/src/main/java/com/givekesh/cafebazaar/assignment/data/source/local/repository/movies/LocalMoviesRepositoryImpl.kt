package com.givekesh.cafebazaar.assignment.data.source.local.repository.movies

import com.givekesh.cafebazaar.assignment.data.entity.movie.MovieEntity
import com.givekesh.cafebazaar.assignment.data.source.local.MoviesDao
import javax.inject.Inject

internal class LocalMoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
) : LocalMoviesRepository {
    override fun fetchMovies(page: Int): List<MovieEntity> = moviesDao.fetchMovies(page)
    override fun insertAll(movies: List<MovieEntity>) = moviesDao.insertAll(movies)
}