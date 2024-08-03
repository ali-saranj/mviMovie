package your.kasra.today.ui.screens.listnewsscreen

import androidx.compose.runtime.mutableStateOf
import androidx.paging.Pager
import kotlinx.coroutines.flow.MutableStateFlow
import your.kasra.today.data.model.Movie

data class StateListMoviesScreen(
 var errorMessage: String? = null,
 var movieList: MutableStateFlow<Pager<Int, Movie>?> = MutableStateFlow(null),
 var query: String? = "",
 var isError: Boolean = false,
 var isLoading: Boolean = false,
 var isSuccess: Boolean = false
)
