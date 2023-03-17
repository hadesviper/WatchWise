package com.herald.watchwise.domain.repository

import com.herald.watchwise.data.remote.dto.MovieDTO
import com.herald.watchwise.data.remote.dto.MoviesPopularDTO
import com.herald.watchwise.data.remote.dto.MoviesTopRatedDTO

/**
 * The Rest Api repository Interface,
 * it's used  to decrease coupling and in testing
 */
interface RestApiRepo {

    suspend fun getMovie(id: Int): MovieDTO

    suspend fun getPopularMovies(page: Int): MoviesPopularDTO

    suspend fun getTopRatedMovies(page: Int): MoviesTopRatedDTO

}