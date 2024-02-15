package com.givekesh.cafebazaar.assignment.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.givekesh.cafebazaar.assignment.data.entity.movie.MovieEntity

@Dao
internal interface MoviesDao {
    @Query("SELECT * FROM movies ORDER BY created_at LIMIT :pageSize OFFSET (:page-1) * :pageSize")
    fun fetchMovies(page: Int, pageSize: Int = 20): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<MovieEntity>)
}