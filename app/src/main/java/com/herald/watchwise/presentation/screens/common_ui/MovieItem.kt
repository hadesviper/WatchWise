package com.herald.watchwise.presentation.screens.common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.herald.watchwise.R
import com.herald.watchwise.presentation.Screens
import com.herald.watchwise.presentation.ui.theme.ColorItemBackGround
import com.herald.watchwise.presentation.ui.theme.ColorPrimary


/**
 *the movie item card that's going to be used in the lazy grids
 * and when you click an item it navigates to the [MovieDetails] Screen
 */

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(
    navController: NavController,
    name: String,
    rate: Float,
    year: String,
    image: String,
    id: Int
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(10.dp)
            .width(144.dp)
            .background(color = ColorItemBackGround,shape = RoundedCornerShape(5.dp))
            .border(
                width = 1.dp,
                color = ColorPrimary,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(7.dp)
            .clickable {
                       navController.navigate(Screens.MovieScreen.route + "/$id")
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GlideImage(
            model = image,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(210.dp)
                .fillMaxWidth(),
            contentDescription = ""
        ){
            it.error(context.getDrawable(R.drawable.img))
        }

        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            maxLines = 1,
            style = TextStyle(
                shadow = Shadow(
                    color = ColorPrimary,
                    blurRadius = 20f
                )
            ),
            overflow = TextOverflow.Ellipsis
        )
        Row {
            TextWithIcon(
                icon = painterResource(id = R.drawable.ic_baseline_star_24),
                text = rate.toString()
            )
            Spacer(modifier = Modifier.weight(fill = true, weight = 1f))
            TextWithIcon(
                icon = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                text = year
            )
        }
    }
}
