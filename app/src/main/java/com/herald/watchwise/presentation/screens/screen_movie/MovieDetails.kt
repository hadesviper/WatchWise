package com.herald.watchwise.presentation.screens.screen_movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.herald.watchwise.R
import com.herald.watchwise.common.HelperMethods
import com.herald.watchwise.domain.models.Movie
import com.herald.watchwise.presentation.screens.common_ui.TextWithIcon
import com.herald.watchwise.presentation.ui.theme.ColorPrimary


@Composable
fun MovieDetails(movie: Movie) {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = movie.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                shadow = Shadow(
                    color = ColorPrimary,
                    blurRadius = 20f
                )
            ),
            fontSize = 20.sp,
        )

        Text(
            text = movie.tagline,
            color = Color.White,
            fontSize = 18.sp,
        )

        Text(
            text = "Genre(s): ${movie.genre}",
            color = Color.White,
            fontSize = 18.sp,
        )
        RowData(movie = movie)

        Text(
            text = movie.overview,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Normal),
            fontSize = 16.sp,
        )
    }

}

@Composable
fun RowData(movie: Movie) {
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TextWithIcon(
            icon = painterResource(id = R.drawable.ic_baseline_star_24),
            text = movie.vote.toString()
        )

        TextWithIcon(
            icon = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
            text = movie.year
        )

        TextWithIcon(
            icon = painterResource(id = R.drawable.ic_baseline_access_time_24),
            text = "${movie.duration} mins"
        )

        Button(
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            onClick = {
                HelperMethods.openYoutubeLink(
                    context = context,
                    youtubeID = movie.videoID
                )
            },
        ) {
            TextWithIcon(
                icon = painterResource(id = R.drawable.ic_baseline_ondemand_video_24),
                text = "Trailer"
            )
        }

    }
}