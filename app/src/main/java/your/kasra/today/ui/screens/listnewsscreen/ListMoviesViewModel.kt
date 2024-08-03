package your.kasra.today.ui.screens.listnewsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import your.kasra.today.data.repository.MoviesRepositoryImpl
import your.kasra.today.ui.navigation.NavAction
import your.kasra.today.utils.Resource
import javax.inject.Inject

@HiltViewModel
class ListMoviesViewModel @Inject constructor(val newsRepository: MoviesRepositoryImpl, val action: NavAction): ViewModel() {

    private val _state = MutableStateFlow(StateListMoviesScreen())
    val state = _state.asStateFlow()

    fun handleIntent(intent: ListMoviesIntent){
        when(intent){
            is ListMoviesIntent.LoadMovies -> loadNews()
            is ListMoviesIntent.SearchMovies -> searchNews(intent.query)
        }

    }


    private fun loadNews() = viewModelScope.launch(Dispatchers.IO){
        _state.value = _state.value.copy(isLoading = true)
        newsRepository.listMovies().collectLatest {
             when(it){
                 is Resource.Error -> _state.value = _state.value.copy(isLoading = false,isError = true, errorMessage = it.message)
                 is Resource.Success -> _state.value = _state.value.copy(isLoading = false, isError = false, movieList = MutableStateFlow(it.data), isSuccess = true)
             }
         }
    }

    private fun searchNews(query: String) {
        TODO("Not yet implemented")
    }

    fun clickMovie(id: Int)  {
        action.navigateTodolistDetailScreen(id)
    }




}