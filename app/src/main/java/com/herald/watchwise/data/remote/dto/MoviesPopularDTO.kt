package com.herald.watchwise.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.herald.watchwise.common.Constants
import com.herald.watchwise.domain.models.MoviesResult

/**
 * A Data Transfer Object Class as we are going to map the json response to this class
 * */
data class MoviesPopularDTO(
    @SerializedName("page")
    val page: Int = 0 ,
    @SerializedName("results")
    val results: List<Result> = emptyList(),
    @SerializedName("total_pages")
    val totalPages: Int = 0 ,
    @SerializedName("total_results")
    val totalResults: Int = 0
) {
    data class Result(
        @SerializedName("adult")
        val adult: Boolean = false,
        @SerializedName("backdrop_path")
        val backdropPath: String = "",
        @SerializedName("genre_ids")
        val genreIds: List<Int> = emptyList(),
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("original_language")
        val originalLanguage: String = "",
        @SerializedName("original_title")
        val originalTitle: String = "",
        @SerializedName("overview")
        val overview: String = "",
        @SerializedName("popularity")
        val popularity: Double = 0.0,
        @SerializedName("poster_path")
        val posterPath: String = "",
        @SerializedName("release_date")
        val releaseDate: String = "1-1",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("video")
        val video: Boolean = false,
        @SerializedName("vote_average")
        val voteAverage: Float = 0f,
        @SerializedName("vote_count")
        val voteCount: Double = 0.0
    )

    /**
     * this function maps this DTO class to the corresponding model class
     * which is [MoviesResult]
     */
    fun toMoviesResult(): MoviesResult {
        return MoviesResult(
            page,
            results.map {
                MoviesResult.Result(
                    posterPath = Constants.Img_Url+it.posterPath,
                    releaseDate = it.releaseDate.split("-")[0],
                    title = it.title,
                    voteAverage = it.voteAverage,
                    popularity = it.popularity,
                    id = it.id
                )
            },
            totalPages,
            totalResults
        )
    }
}

