package com.givekesh.cafebazaar.assignment.domain.mapper.movie

import com.givekesh.cafebazaar.assignment.data.entity.movie.MovieEntity
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.util.ListToListMapper
import javax.inject.Inject

internal class MoviesToLocalEntityMapper @Inject constructor(
) : ListToListMapper<Movie, MovieEntity> {
    override fun toObject(from: Movie) = MovieEntity(
        id = from.id,
        posterPath = from.posterPath,
        title = from.title,
    )
}