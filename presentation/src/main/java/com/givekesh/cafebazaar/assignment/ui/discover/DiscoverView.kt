package com.givekesh.cafebazaar.assignment.ui.discover

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.givekesh.cafebazaar.assignment.R
import com.givekesh.cafebazaar.assignment.domain.model.movie.response.Movie
import com.givekesh.cafebazaar.assignment.domain.util.DataState
import com.givekesh.cafebazaar.assignment.ui.discover.component.ErrorView
import com.givekesh.cafebazaar.assignment.ui.discover.component.HorizontalErrorView
import com.givekesh.cafebazaar.assignment.ui.discover.component.LoadingView
import com.givekesh.cafebazaar.assignment.ui.discover.component.MovieItemView
import kotlinx.coroutines.flow.filter

@Composable
fun DiscoverView(
    modifier: Modifier = Modifier,
    viewModel: DiscoverViewModel = hiltViewModel(),
) {
    val upcomingMoviesDataState = viewModel.upcomingMoviesDataState.collectAsStateWithLifecycle()
    val upcomingMovies = remember { mutableStateListOf<Movie>() }
    var isLoading by remember { mutableStateOf(false) }
    var hasErrorOccurred by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = upcomingMoviesDataState.value) {
        when (val dataState = upcomingMoviesDataState.value) {
            DataState.Idle -> Unit
            DataState.Loading -> {
                hasErrorOccurred = false
                isLoading = true
            }

            is DataState.Successful -> {
                hasErrorOccurred = false
                isLoading = false
                upcomingMovies.addAll(dataState.data)
            }

            is DataState.Failed -> {
                isLoading = false
                hasErrorOccurred = true
            }
        }
    }

    var page by remember { mutableIntStateOf(0) }
    val state = rememberLazyGridState()

    LaunchedEffect(Unit) {
        snapshotFlow { !state.isScrollInProgress && !state.canScrollForward }
            .filter { it }
            .collect {
                page += 1
                viewModel.processIntent(
                    DiscoverIntent.GetUpcomingMovies(page)
                )
            }
    }

    DiscoverViewContent(
        modifier = modifier,
        movieList = upcomingMovies,
        state = state,
        hasErrorOccurred = hasErrorOccurred,
        onRetryClick = {
            viewModel.processIntent(
                DiscoverIntent.GetUpcomingMovies(page)
            )
        },
        isLoading = isLoading,
    )

    AnimatedVisibility(
        visible = hasErrorOccurred && upcomingMovies.isEmpty(),
        enter = fadeIn(tween(400)),
        exit = fadeOut(tween(400)),
    ) {
        ErrorView(
            onRetryClick = {
                viewModel.processIntent(
                    DiscoverIntent.GetUpcomingMovies(page)
                )
            },
        )
    }
}

@Composable
private fun DiscoverViewContent(
    modifier: Modifier = Modifier,
    movieList: SnapshotStateList<Movie>,
    state: LazyGridState,
    hasErrorOccurred: Boolean,
    onRetryClick: () -> Unit,
    isLoading: Boolean,
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color(0xFF131313),
            ),
    ) {
        Spacer(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(305.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0x1AFFFFFF), Color(0xFF131313)),
                    ),
                )
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 18.dp),
            text = stringResource(R.string.discover),
            style = TextStyle(
                color = Color(0xFFFF3D00),
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
            )
        )
        Image(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 10.dp,
                    end = 19.dp
                )
                .width(35.dp)
                .height(37.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 60.dp),
            columns = when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> 3
                else -> 5
            }.let { GridCells.Fixed(it) },
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(
                horizontal = 14.dp,
                vertical = 40.dp
            ),
            state = state,
        ) {
            items(
                items = movieList.distinctBy { it.id },
                key = { it.id },
            ) { item ->
                MovieItemView(
                    item = item,
                    onItemClick = { Toast.makeText(context, it.title, Toast.LENGTH_LONG).show() }
                )
            }
        }
        if (movieList.isNotEmpty() && isLoading) {
            LoadingView(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 22.dp),
            )
        }
        if (movieList.isNotEmpty() && hasErrorOccurred) {
            HorizontalErrorView(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 35.dp)
                    .padding(horizontal = 22.dp),
                errorMessage = "Something went wrong",
                onRetryClick = onRetryClick,
            )
        }
    }
}

@Preview
@Composable
fun PreviewDiscoverView(
) {
    DiscoverViewContent(
        movieList = SnapshotStateList<Movie>().apply {
            addAll(
                listOf(
                    Movie(id = 1859, posterPath = "elitr", title = "detracto"),
                    Movie(id = 9697, posterPath = "hendrerit", title = "suspendisse"),
                    Movie(id = 9116, posterPath = "sociis", title = "ludus"),
                    Movie(id = 8013, posterPath = "iriure", title = "ridens"),
                    Movie(id = 5816, posterPath = "elitr", title = "inimicus"),
                    Movie(id = 2414, posterPath = "curae", title = "civibus"),
                    Movie(id = 7467, posterPath = "odio", title = "reprehendunt"),
                    Movie(id = 7300, posterPath = "maiorum", title = "simul"),
                )
            )
        },
        state = rememberLazyGridState(),
        hasErrorOccurred = false,
        onRetryClick = {},
        isLoading = true,
    )
}