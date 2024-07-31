package your.kasra.today.ui.screens.listnewsscreen

sealed class ListMoviesIntent {
    data object LoadMovies : ListMoviesIntent()
    data class SearchMovies(val query: String) : ListMoviesIntent()
}