package com.givekesh.cafebazaar.assignment.ui.discover

sealed class DiscoverIntent {
    class GetUpcomingMovies(val page: Int) : DiscoverIntent()
}