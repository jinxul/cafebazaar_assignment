package com.givekesh.cafebazaar.assignment.domain.mapper.movie

import com.givekesh.cafebazaar.assignment.data.entity.movie.response.MoviesResponse
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.util.ListToListMapper
import javax.inject.Inject

internal class MoviesMapper @Inject constructor(
) : ListToListMapper<MoviesResponse.Result, Movie> {
    override fun toObject(from: MoviesResponse.Result) = Movie(
        id = from.id,
        posterPath = StringBuilder("https://image.tmdb.org/t/p/w500")
            .append(from.posterPath)
            .toString(),
        title = from.title
    )
}