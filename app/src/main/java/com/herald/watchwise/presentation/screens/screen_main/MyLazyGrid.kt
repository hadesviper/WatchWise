package com.herald.watchwise.presentation.screens.screen_main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.herald.watchwise.domain.models.MoviesResult
import com.herald.watchwise.presentation.screens.common_ui.DialogError
import com.herald.watchwise.presentation.screens.common_ui.MovieItem
import com.herald.watchwise.presentation.viewmodels.StateMovies

/**
 * the function that contains movies in a grid
 * @param viewModel of type CommonVM which is an abstract class
 * so we can pass the viewmodel of popular or top rated movies.
 * this grid show a loading progress Indicator and
 * loads more data when you reach the bottom
 */
@Composable
fun MyLazyVerticalGrid(
    state: State<StateMovies>,
    resultedList:List<MoviesResult.Result>,
    navController: NavController,
    loadMoreItems:(LazyGridState)->Unit
) {

    val scrollState = rememberLazyGridState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        AnimatedVisibility(
            visible = resultedList.isNotEmpty(),
            modifier = Modifier.weight(0.9f)
        ) {
            /**
             * Used this lazy vertical grid
             * so the app can work on multiple
             * screen sizes, and when a device
             * is rotated
             */
            LazyVerticalGrid(
                columns = GridCells.Adaptive(144.dp),
                state = scrollState,
            ) {
                items(resultedList) {
                    MovieItem(
                        navController = navController,
                        name = it.title,
                        rate = it.voteAverage,
                        year = it.releaseDate,
                        image = it.posterPath,
                        id = it.id
                    )
                }
            }
        }
        AnimatedVisibility(visible = state.value.loading) {
            CircularProgressIndicator()
        }
    }

    /**
     * I used this LaunchedEffect because I wanted this function to be called
     * only when the scroll state changes to true or false
     */
    LaunchedEffect(key1 = scrollState.isScrollInProgress) {
        loadMoreItems(scrollState)
    }

    state.value.error.run {
        if (isNotEmpty())
            DialogError(this) {
                loadMoreItems(scrollState)
            }
    }
}

