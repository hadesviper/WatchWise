package com.herald.watchwise.presentation.viewmodels

import com.herald.watchwise.domain.models.Movie

/**
 * Movie state class so we can
 * observe changes and change the UI
 */
data class StateMovie(
    val loading: Boolean = false,
    val result:  Movie? = null,
    val error:   String = ""
)
