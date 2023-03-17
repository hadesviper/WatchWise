package com.herald.watchwise.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.herald.watchwise.data.local.entities.EntityMovie
import kotlinx.coroutines.flow.Flow

/**
 * Data access object (DAO) interface for interacting with the local database.
 */
@Dao
interface WatchLaterDao {


    /**
     * Adds a movie entity to the local database.
     * It's a suspend function that's going to be executed on the [ViewModelMoviesSaved] scope
     * @param movie The movie entity to be added to the database.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: EntityMovie)


    /**
     * Removes a movie entity from the local database by its ID.
     * It's a suspend function that's going to be executed on the [ViewModelMoviesSaved] scope
     * @param id The unique identifier of the movie entity to be removed.
     */
    @Query("Delete from EntityMovie where id = :id")
    suspend fun removeMovie(id: Int)

    /**
     * Checks if a movie with the given ID exists in the local database.
     * @param id The unique identifier of the movie entity to be checked.
     * @return A [Flow] that emits a boolean indicating whether or not the movie exists.
     */
    @Query("Select Exists (Select 1 from EntityMovie where id = :id)")
    fun checkMovieExistence(id: Int): Flow<Boolean>

    /**
     * Returns a [Flow] of all the movie entities stored in the local database, ordered by timestamp.
     * @return A [Flow] of lists of [EntityMovie] objects representing the movies.
     */
    @Query("Select * from EntityMovie order by timeStamp desc")
    fun getAllMovies(): Flow<List<EntityMovie>>

}