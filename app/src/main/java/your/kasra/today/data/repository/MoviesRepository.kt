package your.kasra.today.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import your.kasra.today.data.model.Movie
import your.kasra.today.utils.Resource

interface MoviesRepository {
    fun listMovies(): Flow<Resource<Pager<Int, Movie>>>
    fun detailsMovies(id:Int): Flow<Resource<Movie>>
}