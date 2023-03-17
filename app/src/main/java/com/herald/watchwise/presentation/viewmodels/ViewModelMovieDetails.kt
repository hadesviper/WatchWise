package com.herald.watchwise.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herald.watchwise.common.Resources
import com.herald.watchwise.domain.use_cases.use_cases_remote.ShowMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * The view model that gets and holds the movie details
 * and maintains the UI state
 */
@HiltViewModel
class ViewModelMovieDetails @Inject constructor(
    private val showMovieUseCase: ShowMovieUseCase
) :ViewModel() {

    /**
     * [_state] this variable is private so it won't be updated anywhere else
     * and it holds the state of the movie whether it's loading or fetched data or error
     * [state] is a public variable and it's equal to [_state] but it can't be updated
     * and it's used to update the UI
     */
    private val _state = mutableStateOf(StateMovie())
    val state : State<StateMovie> = _state

    /**
     * this function uses onEach flow operator as we won't
     * perform any updates on the stream
     * and it's used to just update the [_state]
     * each time a value is emitted and then
     * updates the [state] which updates the UI
     */
    fun getMovie(id :Int){
        showMovieUseCase(id).onEach {
            when (it) {
                is Resources.Loading -> {
                    _state.value = StateMovie(loading = true)
                }
                is Resources.Success -> {
                    _state.value = StateMovie(result = it.data!!)
                }
                is Resources.Error -> {
                    _state.value = StateMovie(error = it.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

}