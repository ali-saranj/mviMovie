package your.kasra.today.data.datasource.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import your.kasra.today.data.mapers.toMovie
import your.kasra.today.data.model.Movie
import your.kasra.today.data.remote.IClientMovie
import your.kasra.today.data.remote.model.listmovies.ResultApi

import your.kasra.today.utils.Resource
import java.io.IOException
import javax.inject.Inject


class MoviesDataSourceImpl @Inject constructor(private val iclient: IClientMovie) :
    MoviesDataSource  {
    override suspend fun listMovies(page: Int): Resource<List<Movie>> {

        try {
            val response = iclient.getMovies(page)
            return if (response.isSuccessful) {
                Resource.Success(response.body()!!.data.map { it.toMovie() })
            } else {
                Resource.Error(response.message())
            }
        } catch (exception: Exception) {
            return Resource.Error(exception.message ?: "Something went wrong")
        }

    }

    override suspend fun getMovie(id: Int): Resource<Movie> {
        try {
            val response = iclient.getMovieDetails(id.toString())
            return if (response.isSuccessful) {
                Resource.Success(response.body()!!.toMovie())
            } else {
                Resource.Error(response.message())
            }
        } catch (exception: Exception) {
            return Resource.Error(exception.message ?: "Something went wrong")
        }
    }

}
