package com.givekesh.cafebazaar.assignment.domain.use_case.movie

import com.givekesh.cafebazaar.assignment.data.source.remote.repository.movies.MoviesRepository
import com.givekesh.cafebazaar.assignment.domain.mapper.movie.MoviesMapper
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.util.DataState
import com.givekesh.cafebazaar.assignment.domain.util.safeFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MoviesUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper
) : MoviesUseCase {
    override fun getUpcomingMovies(page: Int): Flow<DataState<List<Movie>>> = safeFlow(
        apiCall = {
            moviesRepository.getUpcomingMovies(page)
        },
        block = { apiResponse ->
            moviesMapper.toList(apiResponse.results)
                .also { emit(DataState.Successful(it)) }
        }
    )
}