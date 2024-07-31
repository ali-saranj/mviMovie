package your.kasra.today.ui.screens.detailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import your.kasra.today.data.repository.MoviesRepositoryImpl
import your.kasra.today.ui.navigation.NavAction
import your.kasra.today.utils.Resource
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    val repositoryImpl: MoviesRepositoryImpl,
    val action: NavAction
) :
    ViewModel() {

    private val _state = MutableStateFlow(StateListMoviesDetailScreen())
    val state = _state.asStateFlow()

    fun handlerIntent(intent: MovieDetailIntent) {
        when (intent) {
            is MovieDetailIntent.LoadMovie -> getMovie(intent.id)
        }
    }

    private fun getMovie(id: Int) = viewModelScope.launch {
        _state.value = _state.value.copy(isLoading = true)
        repositoryImpl.detailsMovies(id).collect {
            when (it) {
                is Resource.Error -> _state.value =
                    _state.value.copy(isLoading = false, isError = true, errorMessage = it.message)

                is Resource.Success -> _state.value = _state.value.copy(
                    isLoading = false,
                    isError = false,
                    movie = it.data,
                    isSuccess = true
                )
            }
        }
    }

    fun back() = viewModelScope.launch {
        action.navigateBackMovieDetailScreen()
    }
}