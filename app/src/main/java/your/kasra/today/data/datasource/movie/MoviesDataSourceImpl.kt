package your.kasra.today.data.datasource.movie

import your.kasra.today.data.mapers.toMovie
import your.kasra.today.data.model.Movie
import your.kasra.today.data.remote.IClientMovie

import your.kasra.today.utils.Resource
import javax.inject.Inject


class MoviesDataSourceImpl @Inject constructor(private val iclient: IClientMovie) :
    MoviesDataSource {
    override suspend fun listMovies(): Resource<List<Movie>> {

        try {
            val response = iclient.getMovies()
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
