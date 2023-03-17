package com.herald.watchwise.presentation.screens.screen_movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.herald.watchwise.R
import com.herald.watchwise.domain.models.Movie
import com.herald.watchwise.presentation.ui.theme.ColorPrimary
import com.herald.watchwise.presentation.viewmodels.ViewModelMoviesSaved

/**
 * A composable function that carries the movie landscape picture
 * and an IconButton that toggles the movie existence in the watch later list
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FullImage(
    movie: Movie,
    viewModel: ViewModelMoviesSaved = hiltViewModel()
) {

    val context = LocalContext.current

    val isMovieSaved by viewModel.getItemExists(movie.id).observeAsState(false)

    val btnImageAdded: Painter =
        if (isMovieSaved)
            painterResource(id = R.drawable.ic_baseline_bookmark_added_24)
        else
            painterResource(id = R.drawable.ic_baseline_bookmark_border_24)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 190.dp)
    )
    {
        GlideImage(
            model = movie.backdrop_path,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth(),
            contentDescription = "",
        )
        {
            it.error(context.getDrawable(R.drawable.img))
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .background(ColorPrimary, shape = RoundedCornerShape(topStart = 10.dp)),
            onClick = {
                viewModel.saveButtonClick(isExisting = isMovieSaved,movie = movie, context = context)
            }) {
            Image(
                painter = btnImageAdded,
                contentDescription = ""
            )
        }
    }
}