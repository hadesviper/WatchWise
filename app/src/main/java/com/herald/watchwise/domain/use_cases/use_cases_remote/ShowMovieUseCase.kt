package com.herald.watchwise.domain.use_cases.use_cases_remote

import android.content.Context
import com.herald.watchwise.R
import com.herald.watchwise.common.Resources
import com.herald.watchwise.domain.models.Movie
import com.herald.watchwise.domain.repository.RestApiRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * This UseCase class is used to fetch the movie details, it takes the [RestApiRepo] as a parameter and it's handled by Dagger hilt.
 * It has only the invoke operator function that takes an Int parameter movie id and returns a Flow of [Resources<[Movie]>]
 * This function uses a flow builder to emit values asynchronously as they become available.
 * The first value it emits is a Resources.Loading object to indicate that the data is being fetched.
 * Then, it calls the getMovie function to get the desired movie from the REST API.
 * If this call is successful, it emits a Resources.Success object that contains the movie data converted to a Movie object.
 * If there is an HTTP or IO exception, it emits a Resources.Error object with a custom error message that is displayed to the user.
 */
class ShowMovieUseCase @Inject constructor(
    private val restApiRepo: RestApiRepo,
    private val context: Context
) {

    operator fun invoke(id: Int): Flow<Resources<Movie>> = flow {
        try {
            emit(Resources.Loading())
            val data = restApiRepo.getMovie(id)
            emit(Resources.Success(data.toMovie()))
        } catch (e: HttpException) {
            emit(Resources.Error(message = context.getString(R.string.http_exception_error_msg)))
        } catch (e: IOException) {
            emit(Resources.Error(message = context.getString(R.string.io_exception_error_msg)))
        }
    }
}