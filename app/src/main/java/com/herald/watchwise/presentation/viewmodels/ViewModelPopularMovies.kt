package com.herald.watchwise.presentation.viewmodels

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.watchwise.common.Resources
import com.herald.watchwise.domain.models.MoviesResult
import com.herald.watchwise.domain.use_cases.use_cases_remote.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The view model that extend the CommonVM class
 * and overrides the values and the function
 * It gets and holds the Popular Movies List
 * and maintains the UI state
 */
@HiltViewModel
class ViewModelPopularMovies @Inject constructor(
    private val popularMoviesUseCase: PopularMoviesUseCase
) : ViewModel() {

    /**
     * [_state] this state variable is private so it won't be updated any where else
     * and it holds the state of the movie whether it's loading or fetched data or error
     * [state] is a public state variable and it's equal to [_state] but it can't be updated
     * and it's used to update the UI.
     *
     * The same logic is applied on [_resultedList] and [resultedList]
     * as they both hold the accumulated movies list
     */

    private val _state = mutableStateOf(StateMovies())
    val state: State<StateMovies> = _state
    private val _resultedList = mutableListOf<MoviesResult.Result>()
    val resultedList: List<MoviesResult.Result> = _resultedList

    /**
     * this function uses onEach flow operator as we won't
     * perform any updates on the stream
     * and it's used to update the [_state]
     * each time a value is emitted and then
     * updates the [state] which updates the UI.
     * and it also updates the [_resultedList] with
     * the movies from the next page to be added to our UI LazyGrid
     */
    private fun getPopularMovies(page: Int = 1) {
        popularMoviesUseCase(page).onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = StateMovies(loading = true)
                }
                is Resources.Success -> {
                    _state.value = StateMovies(result = it.data!!)
                    _resultedList.addAll(it.data.results)
                }
                is Resources.Error -> {
                    _state.value = StateMovies(error = it.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCurrentPage(): Int {
        return if (_state.value.result == null) {
            1
        } else {
            _state.value.result!!.page
        }
    }

    private fun getNextPage() {
        if (_state.value.result != null) {
            if (getCurrentPage() + 1 < _state.value.result!!.totalPages) {
                getPopularMovies(getCurrentPage() + 1)
            }
        } else {
            getPopularMovies(getCurrentPage() + 1)
        }
    }

    /**
     * [loadMoreItems]
     * This function is the only function that
     * is going to be used outside of our viewModel.
     * as it's overridden from CommonVM.
     *
     * It's purpose to load the first page when the LazyGrid
     * is empty and loads more when we reach the bottom
     */
    fun loadMoreItems(
        scrollState: LazyGridState,
    ) {
        scrollState.run {
            if (layoutInfo.totalItemsCount != 0) {
                if (layoutInfo.visibleItemsInfo.last().index + 1 == layoutInfo.totalItemsCount
                    && !_state.value.loading
                ) {
                    getNextPage()
                }
            } else {
                getPopularMovies()
            }
        }
    }
}