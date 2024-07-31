package your.kasra.today.ui.screens.listnewsscreen

import your.kasra.today.data.model.Movie

data class StateListMoviesScreen(
 var errorMessage: String? = null,
 var movieList: List<Movie>? = listOf(),
 var query: String? = "",
 var isError: Boolean = false,
 var isLoading: Boolean = false,
 var isSuccess : Boolean = false
)
