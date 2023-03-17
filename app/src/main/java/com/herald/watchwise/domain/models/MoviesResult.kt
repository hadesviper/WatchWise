package com.herald.watchwise.domain.models


/**
 * A data model class that holds information about the retrieved
 * popular and top rated movies
 */
data class MoviesResult(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
) {
    data class Result(
        val id: Int,
        val posterPath: String,
        val releaseDate: String,
        val title: String,
        val voteAverage: Float,
        val popularity: Double,
    )
}