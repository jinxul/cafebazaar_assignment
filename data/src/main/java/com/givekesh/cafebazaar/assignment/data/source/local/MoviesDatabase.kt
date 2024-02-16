package com.givekesh.cafebazaar.assignment.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.givekesh.cafebazaar.assignment.data.entity.movie.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
)
internal abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}