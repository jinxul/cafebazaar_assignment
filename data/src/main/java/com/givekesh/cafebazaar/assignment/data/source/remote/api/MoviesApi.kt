package com.givekesh.cafebazaar.assignment.data.source.remote.api

import com.givekesh.cafebazaar.assignment.data.entity.movie.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MoviesApi {
    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
    ): MoviesResponse
}