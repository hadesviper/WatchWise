package com.herald.watchwise.data.remote

import com.herald.watchwise.common.Constants.Api_Key
import com.herald.watchwise.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * The REST Api service interface that is going to be used by retrofit
 * in order to fetch the data from the remote server.
 * the functions here are suspend functions as they are going to be executed from a flow builder
 * and we are going to wait for their results
 */
interface RestApiService {

    /**
     * Gets the movie by id
     */
    @GET("movie/{id}?api_key=$Api_Key&language=en-US&append_to_response=videos")
    suspend fun getMovie(@Path("id") id: Int): MovieDTO

    /**
     * Gets Popular movies by page number
     */
    @GET("movie/popular?api_key=$Api_Key&language=en-US")
    suspend fun getPopularMovies(@Query("page") page: Int): MoviesPopularDTO

    /**
     * Gets Popular movies by page number
     *
     */
    @GET("movie/top_rated?api_key=$Api_Key&language=en-US")
    suspend fun getTopRatedMovies(@Query("page") page: Int): MoviesTopRatedDTO

}