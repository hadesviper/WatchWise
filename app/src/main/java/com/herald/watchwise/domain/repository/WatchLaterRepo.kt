package com.herald.watchwise.domain.repository

import com.herald.watchwise.data.local.entities.EntityMovie
import kotlinx.coroutines.flow.Flow

/**
 * The WatchLater repository Interface,
 * it's used  to decrease coupling and in testing
 */
interface WatchLaterRepo {
    suspend fun addMovie(movie: EntityMovie)

    suspend fun removeMovie(id: Int)

    fun checkMovieExistence(id: Int): Flow<Boolean>

    fun getAllMovies(): Flow<List<EntityMovie>>

}