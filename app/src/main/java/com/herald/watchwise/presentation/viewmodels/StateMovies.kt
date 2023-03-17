package com.herald.watchwise.presentation.viewmodels

import com.herald.watchwise.domain.models.MoviesResult

/**
 * Movies state class so we can
 * observe changes and change the UI
 */
data class StateMovies(
    val loading: Boolean = false,
    val result:  MoviesResult? = null,
    val error:   String = ""
)