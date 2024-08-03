package your.kasra.today.data.pagaig

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import your.kasra.today.data.datasource.movie.MoviesDataSource
import your.kasra.today.data.mapers.toMovie
import your.kasra.today.data.model.Movie
import your.kasra.today.data.remote.IClientMovie
import java.io.IOException
import javax.inject.Inject

class MoviePagingSource @Inject constructor(
    val iclient: IClientMovie,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val movies = iclient.getMovies(currentPage)
            Log.d( "load: ", movies.body().toString())
            LoadResult.Page(
                data = movies.body()!!.data.map { it.toMovie() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.body()!!.data.isEmpty()) null else movies.body()!!.metadata.currentPage.toInt() + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        Log.d("getRefreshKey: ", state.anchorPosition.toString())
        return state.anchorPosition
    }

}