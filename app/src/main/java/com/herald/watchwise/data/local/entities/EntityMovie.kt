package com.herald.watchwise.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * An entity class that represents a movie entity in a local database.
 *
 * @property id The unique identifier for the movie entity.
 * @property posterPath The poster path of the movie.
 * @property year The release year of the movie.
 * @property title The title of the movie.
 * @property vote The rating of the movie.
 * @property timeStamp The timestamp when the movie entity was added to the database
 * we need timeStamp property as we are going to return data sorted according to show the last saved first
 */
@Entity
data class EntityMovie(
    @PrimaryKey
    val id: Int,
    val posterPath: String = "",
    val year: String = "",
    val title: String = "",
    val vote: Float = 0f,
    val timeStamp: Long = System.currentTimeMillis()
) {
}
