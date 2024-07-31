package your.kasra.today.data.repository

import kotlinx.coroutines.flow.Flow
import your.kasra.today.data.model.Movie
import your.kasra.today.utils.Resource

interface MoviesRepository {
    fun listMovies(): Flow<Resource<List<Movie>>>
    fun detailsMovies(id:Int): Flow<Resource<Movie>>
}