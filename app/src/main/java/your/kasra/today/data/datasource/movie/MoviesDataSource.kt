package your.kasra.today.data.datasource.movie

import your.kasra.today.data.model.Movie
import your.kasra.today.utils.Resource

interface MoviesDataSource {
   suspend fun listMovies(): Resource<List<Movie>>

   suspend fun getMovie(id: Int): Resource<Movie>
}