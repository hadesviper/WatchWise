package com.herald.watchwise.presentation.viewmodels

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.herald.watchwise.domain.models.MoviesResult

/**
 * this abstract class extends the ViewModel class
 * and is going to be extended by top rated and popular movies
 * movies viewModels so we can pass any of the 2 viewModels
 * to our LazyGrid in UI
 */
abstract class CommonVM : ViewModel() {

    abstract val state : State<StateMovies>
    abstract val resultedList :List<MoviesResult.Result>
    abstract fun loadMoreItems(scrollState: LazyGridState)
}