package com.herald.watchwise.presentation.screens.screen_movie

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.herald.watchwise.R
import com.herald.watchwise.domain.models.Movie
import com.herald.watchwise.presentation.screens.common_ui.DialogError
import com.herald.watchwise.presentation.ui.theme.ColorBackground
import com.herald.watchwise.presentation.ui.theme.ColorPrimary
import com.herald.watchwise.presentation.viewmodels.ViewModelMovieDetails

/**
 * The movie details screen that has some data about
 * the selected movie
 */
@Composable
fun MyTopAppBarMovie(
    navController: NavController,
    movieName: String
) {
    TopAppBar(
        backgroundColor = ColorPrimary,
        title = {
            Text(
                text = movieName,
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
        },

        )
}


@Composable
fun MovieData(
    navController: NavController,
    movie: Movie
) {
    Scaffold(
        topBar = {
            MyTopAppBarMovie(navController = navController, movieName = movie.title)
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            FullImage(movie = movie)
            MovieDetails(movie = movie)
        }
    }
}

@Composable
fun MovieScreen(
    navController: NavController,
    movieID: Int,
    viewModel: ViewModelMovieDetails = hiltViewModel()
) {
    val state = viewModel.state.value
    /**
     * used this effect handler so the get movie function will be executed only once
     */
    LaunchedEffect(key1 = true) {
        viewModel.getMovie(movieID)
    }
    AnimatedVisibility(visible = viewModel.state.value.loading) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator()
        }
    }
    state.result.run {
        if (this != null)
            MovieData(movie = this, navController = navController)
    }
    state.error.run {
        if (isNotEmpty())
            DialogError(this) {
                viewModel.getMovie(movieID)
            }
    }
}