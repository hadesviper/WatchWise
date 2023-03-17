package com.herald.watchwise.data.local.repository

import com.herald.watchwise.data.local.WatchLaterDao
import com.herald.watchwise.data.local.entities.EntityMovie
import com.herald.watchwise.domain.repository.WatchLaterRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [WatchLaterRepo] interface that interacts with the local database.
 *
 * @property watchLaterDao The DAO object used to interact with the local database.
 */

class WatchLaterImpl @Inject constructor(
    private val watchLaterDao: WatchLaterDao
) : WatchLaterRepo {

    override suspend fun addMovie(movie: EntityMovie) {
        watchLaterDao.addMovie(movie)
    }

    override suspend fun removeMovie(id: Int) {
        watchLaterDao.removeMovie(id)
    }

    override fun checkMovieExistence(id: Int): Flow<Boolean> {
        return watchLaterDao.checkMovieExistence(id)
    }

    override fun getAllMovies(): Flow<List<EntityMovie>> {
        return watchLaterDao.getAllMovies()
    }
}