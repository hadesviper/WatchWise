package com.herald.watchwise.presentation.screens.screen_main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.core.app.ComponentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.herald.watchwise.R
import com.herald.watchwise.domain.models.MoviesResult
import com.herald.watchwise.presentation.Screens
import com.herald.watchwise.presentation.screens.common_ui.TextWithIcon
import com.herald.watchwise.presentation.ui.theme.ColorBackground
import com.herald.watchwise.presentation.ui.theme.ColorPrimary
import com.herald.watchwise.presentation.viewmodels.StateMovies
import com.herald.watchwise.presentation.viewmodels.ViewModelPopularMovies
import com.herald.watchwise.presentation.viewmodels.ViewModelTopRatedMovies
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

/**
 * The Main Screen and it's components file
 * it consist of three composable functions
 *
 * Top App bar
 *
 * The Movie Items and it consist of a viewpager with 2 pages that are popular and top-rated
 * and both contains a lazy grid that contains the movies list
 *
 * The MovieScreen function that wraps all in a scaffold
 */
@Composable
fun MyTopAppBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = "WatchWise",
                style = TextStyle(
                    shadow = Shadow(
                        color = ColorBackground,
                        blurRadius = 20f
                    )
                ), fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        elevation = 10.dp,
        backgroundColor = ColorPrimary,
        actions = {
                 IconButton(onClick = {
                     navController.navigate(Screens.SavedScreen.route)
                 }) {
                     Icon(
                         painter = painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                         contentDescription = "",
                         tint = Color.White
                     )
                 }
        },
        contentColor = Color.White
    )
}


@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun MovieItems(
    navController: NavController,
    stateTopRated: State<StateMovies>,
    resultedListTopRated:List<MoviesResult.Result>,
    loadMoreItemsTopRated:(LazyGridState)->Unit,
    statePopular: State<StateMovies>,
    resultedListPopular:List<MoviesResult.Result>,
    loadMoreItemsPopular:(LazyGridState)->Unit

) {

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }, backgroundColor = ColorPrimary, contentColor = Color.White
    ) {
        Tab(
            selected = pagerState.currentPage == 0,
            onClick = { scope.launch { pagerState.animateScrollToPage(0) } },
            icon = {
                TextWithIcon(
                    icon = painterResource(id = R.drawable.baseline_whatshot_24),
                    text = "Popular"
                )
            }
        )
        Tab(
            selected = pagerState.currentPage == 1,
            onClick = { scope.launch { pagerState.animateScrollToPage(1) } },
            icon = {
                TextWithIcon(
                    icon = painterResource(id = R.drawable.baseline_star_rate_24),
                    text = "Top Rated"
                )
            }
        )
    }
    HorizontalPager(
        pageCount = 2,
        state = pagerState,
    ) { page ->
        when (page) {
            0 -> MyLazyVerticalGrid(
                state = statePopular,
                navController = navController,
                resultedList = resultedListPopular
            ) { loadMoreItemsPopular(it) }

            1 -> MyLazyVerticalGrid(
                state = stateTopRated,
                navController = navController,
                resultedList = resultedListTopRated
            ) { loadMoreItemsTopRated(it) }
        }
    }
}

@Composable
fun MainScreen(
    navController: NavController,
    viewModelPopular: ViewModelPopularMovies = hiltViewModel(),
    viewModelTopRated: ViewModelTopRatedMovies = hiltViewModel()
) {
    val context = LocalContext.current
    BackHandler(true) {
        (context as ComponentActivity).finishAndRemoveTask()
        exitProcess(0)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar(navController) },
    )
    {padding->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            MovieItems(
                navController = navController,
                stateTopRated = viewModelTopRated.state,
                resultedListTopRated = viewModelTopRated.resultedList,
                loadMoreItemsTopRated = {viewModelTopRated.loadMoreItems(it)},
                statePopular = viewModelPopular.state,
                resultedListPopular = viewModelPopular.resultedList
            ) { viewModelPopular.loadMoreItems(it) }
        }
    }
}
