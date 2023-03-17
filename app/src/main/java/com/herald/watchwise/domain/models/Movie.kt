package com.herald.watchwise.domain.models

import com.herald.watchwise.data.local.entities.EntityMovie

/**
 * A data model class that holds information about the retrieved movie
 */
data class Movie(
    val id: Int,
    val posterPath: String,
    val backdrop_path: String,
    val genre: String,
    val overview: String,
    val popularity: Double,
    val title: String,
    val tagline: String,
    val videoID: String,
    val year: String,
    val vote: Float,
    val duration: Int
) {
    /**
     * this function maps this data model
     * to [EntityMovie] to store the movie
     * information in the local database
     */
    fun toEntityMovie(): EntityMovie {
        return EntityMovie(
            id = this.id,
            posterPath = this.posterPath,
            year = this.year,
            title = this.title,
            vote = this.vote
        )
    }
}
