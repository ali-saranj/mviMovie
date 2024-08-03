package your.kasra.today.data.datasource.movie

import your.kasra.today.data.model.Movie
import your.kasra.today.data.remote.model.listmovies.ResultApi
import your.kasra.today.utils.Resource

interface MoviesDataSource {
   suspend fun listMovies(page: Int): Resource<List<Movie>>

   suspend fun getMovie(id: Int): Resource<Movie>
}