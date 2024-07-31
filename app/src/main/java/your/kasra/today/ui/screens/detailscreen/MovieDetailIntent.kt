package your.kasra.today.ui.screens.detailscreen

sealed class MovieDetailIntent {
    data class LoadMovie(val id: Int) : MovieDetailIntent()

}