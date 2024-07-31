package your.kasra.today.ui.screens.detailscreen

import your.kasra.today.data.model.Movie

data class StateListMoviesDetailScreen(
 var errorMessage: String? = null,
 var movie: Movie? = null,
 var isError: Boolean = false,
 var isLoading: Boolean = false,
 var isSuccess : Boolean = false
)
