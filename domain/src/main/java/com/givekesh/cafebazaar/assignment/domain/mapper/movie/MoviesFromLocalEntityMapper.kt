package com.givekesh.cafebazaar.assignment.domain.mapper.movie

import com.givekesh.cafebazaar.assignment.data.entity.movie.MovieEntity
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.util.ListToListMapper
import javax.inject.Inject

internal class MoviesFromLocalEntityMapper @Inject constructor(
) : ListToListMapper<MovieEntity, Movie> {
    override fun toObject(from: MovieEntity) = Movie(
        id = from.id,
        posterPath = from.posterPath,
        title = from.title,
    )
}