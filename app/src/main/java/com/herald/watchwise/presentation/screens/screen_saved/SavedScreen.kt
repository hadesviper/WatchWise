package com.herald.watchwise.presentation.screens.screen_saved

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.herald.watchwise.R
import com.herald.watchwise.presentation.screens.common_ui.MovieItem
import com.herald.watchwise.presentation.ui.theme.ColorBackground
import com.herald.watchwise.presentation.ui.theme.ColorPrimary
import com.herald.watchwise.presentation.viewmodels.ViewModelMoviesSaved

/**
 * The Watch Later Screen that contains a list of movies
 * that are saved to watch later in the local database
 */
@Composable
fun MyTopAppBarSaved(
    navController: NavController,
) {
    TopAppBar(
        backgroundColor = ColorPrimary,
        title = {
            Text(
                text = "Watch Later",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    shadow = Shadow(
                        color = ColorBackground,
                        blurRadius = 30f
                    )
                ),
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        elevation = 10.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = ""
                )
            }
        }

    )
}

@Composable
fun SavedScreen(
    navHostController: NavHostController,
    viewModel: ViewModelMoviesSaved = hiltViewModel()
) {
    val savedMovies by viewModel.allSavedMovies.observeAsState(emptyList())

    Scaffold(
        topBar = { MyTopAppBarSaved(navController = navHostController) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = savedMovies.isNotEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(144.dp),
                ) {
                    items(savedMovies) { movie ->
                        MovieItem(
                            navController = navHostController,
                            name = movie.title,
                            rate = movie.vote,
                            year = movie.year,
                            image = movie.posterPath,
                            id = movie.id
                        )
                    }
                }
            }
            AnimatedVisibility(visible = savedMovies.isEmpty()) {
                Text(text = "Nothing is here yet!")
            }
        }
    }
}