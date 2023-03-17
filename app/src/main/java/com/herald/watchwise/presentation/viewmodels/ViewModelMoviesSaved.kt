package com.herald.watchwise.presentation.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.herald.watchwise.data.local.entities.EntityMovie
import com.herald.watchwise.domain.models.Movie
import com.herald.watchwise.domain.use_cases.use_cases_local.SavedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * the ViewModel that's responsible for
 * the saved to watch later movies
 */
@HiltViewModel
class ViewModelMoviesSaved @Inject constructor(
    private val savedMoviesUseCase: SavedMoviesUseCase
) : ViewModel() {

    /**
     * this variable receives the flow of saved items as livedata and use it to update the UI
     * State later on.
     */
    val allSavedMovies: LiveData<List<EntityMovie>> = savedMoviesUseCase.getAllMovies().asLiveData()

    /**
     * Used to check whether the movie is added or not and sets the UI add/remove button
     */
    fun getItemExists(id: Int): LiveData<Boolean> {
        return savedMoviesUseCase.checkMovieExistence(id).asLiveData()
    }

    /**
     * these 2 functions are launched on the viewModel coroutine scope
     * and they suspend it until they finish their job
     */
    private fun removeMovie(id: Int) {
        viewModelScope.launch {
            savedMoviesUseCase.removeMovie(id)
        }
    }

    private fun addMovie(movie: EntityMovie) {
        viewModelScope.launch {
            savedMoviesUseCase.addMovie(movie)
        }
    }

    /**
     * this function is invoked from the UI to add/remove a movie and shows
     * a toast afterwards
     */
    fun saveButtonClick(isExisting: Boolean, movie: Movie? = null, context: Context) {
        if (movie != null) {
            if (isExisting) {
                removeMovie(movie.id)
                Toast.makeText(context, "Removed from watch later", Toast.LENGTH_SHORT).show()
            } else {
                addMovie(movie.toEntityMovie())
                Toast.makeText(context, "Added to watch later", Toast.LENGTH_SHORT).show()
            }
        }
    }
}