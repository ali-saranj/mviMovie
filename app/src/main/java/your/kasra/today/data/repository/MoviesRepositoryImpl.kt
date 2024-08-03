package your.kasra.today.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import your.kasra.today.data.datasource.movie.MoviesDataSourceImpl
import your.kasra.today.data.model.Movie
import your.kasra.today.data.pagaig.MoviePagingSource
import your.kasra.today.utils.Constant
import your.kasra.today.utils.Resource
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val dataSourceImpl: MoviesDataSourceImpl,
    private val pagingSource: MoviePagingSource
) :
    MoviesRepository {

    override fun listMovies(): Flow<Resource<Pager<Int, Movie>>> = flow {
        when (val response = dataSourceImpl.listMovies(2)) {
            is Resource.Error -> emit(Resource.Error(response.message ?: "Something went wrong"))
            is Resource.Success -> emit(Resource.Success(Pager(
                config = PagingConfig(pageSize = Constant.MAX_PAGE_SIZE, prefetchDistance = 205),
                pagingSourceFactory = {
                    pagingSource
                })))
        }
    }.flowOn(Dispatchers.IO)

    override fun detailsMovies(id: Int): Flow<Resource<Movie>> = flow {
        when (val response = dataSourceImpl.getMovie(id)) {
            is Resource.Error -> emit(Resource.Error(response.message ?: "Something went wrong"))
            is Resource.Success -> emit(
                Resource.Success(
                    response.data
                )
            )
        }
    }.flowOn(Dispatchers.IO)
}