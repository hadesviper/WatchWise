package com.herald.watchwise.domain.use_cases.use_cases_local

import com.herald.watchwise.data.local.entities.EntityMovie
import com.herald.watchwise.domain.repository.WatchLaterRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * This UseCase class used to store and retrieve movies from the local database,
 * it takes the [watchLaterRepo] as a parameter and it's handled by Dagger hilt.
 * I have nothing more documentation to add as these functions are documented
 * in [WatchLaterDao] interface
 */
class SavedMoviesUseCase @Inject constructor(
    private val watchLaterRepo: WatchLaterRepo
) {
    suspend fun addMovie(item: EntityMovie) {
        watchLaterRepo.addMovie(item)
    }

    suspend fun removeMovie(id: Int) {
        watchLaterRepo.removeMovie(id)
    }

    fun checkMovieExistence(id: Int): Flow<Boolean> {
        return watchLaterRepo.checkMovieExistence(id)
    }

    fun getAllMovies(): Flow<List<EntityMovie>> {
        return watchLaterRepo.getAllMovies()
    }
}