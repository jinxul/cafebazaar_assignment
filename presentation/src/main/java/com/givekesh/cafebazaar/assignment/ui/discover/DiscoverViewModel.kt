package com.givekesh.cafebazaar.assignment.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.use_case.movie.MoviesUseCase
import com.givekesh.cafebazaar.assignment.domain.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
) : ViewModel() {
    private val intentChannel = Channel<DiscoverIntent>()

    private val _upcomingMoviesDataState = MutableStateFlow<DataState<List<Movie>>>(DataState.Idle)
    val upcomingMoviesDataState = _upcomingMoviesDataState.asStateFlow()

    init {
        observeChannelIntent()
    }

    private fun observeChannelIntent() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collectLatest { intent ->
                when (intent) {
                    is DiscoverIntent.GetUpcomingMovies -> moviesUseCase.getUpcomingMovies(intent.page)
                        .onEach { _upcomingMoviesDataState.value = it }
                        .launchIn(viewModelScope)

                    is DiscoverIntent.GetLocalUpcomingMovies -> moviesUseCase.getLocalUpcomingMovies(
                        intent.page
                    )
                        .onEach { _upcomingMoviesDataState.value = it }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    fun processIntent(intent: DiscoverIntent) {
        viewModelScope.launch {
            intentChannel.send(intent)
        }
    }
}