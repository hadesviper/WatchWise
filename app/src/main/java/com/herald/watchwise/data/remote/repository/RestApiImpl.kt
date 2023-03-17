package com.herald.watchwise.data.remote.repository

import com.herald.watchwise.data.remote.RestApiService
import com.herald.watchwise.data.remote.dto.MovieDTO
import com.herald.watchwise.data.remote.dto.MoviesPopularDTO
import com.herald.watchwise.data.remote.dto.MoviesTopRatedDTO
import com.herald.watchwise.domain.repository.RestApiRepo
import javax.inject.Inject

/**
 * This is our REST Api Implementation class that
 * implements our repository interface [RestApiRepo]
 */
class RestApiImpl @Inject constructor(
    private val restApiService: RestApiService
    ): RestApiRepo {

    override suspend fun getMovie(id: Int): MovieDTO {
        return restApiService.getMovie(id)
    }

    override suspend fun getPopularMovies(page: Int): MoviesPopularDTO {
        return restApiService.getPopularMovies(page)
    }

    override suspend fun getTopRatedMovies(page: Int): MoviesTopRatedDTO {
        return restApiService.getTopRatedMovies(page)
    }
}