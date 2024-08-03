package your.kasra.today.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import your.kasra.today.data.remote.model.listmovies.ResultApi
import your.kasra.today.data.remote.model.moviedetail.MovieDetailsDto
import your.kasra.today.utils.Constant

interface IClientMovie {
    @GET("/api/v1/movies")
    suspend fun getMovies(@Query("page") pageNumber: Int): Response<ResultApi>

    @GET("/api/v1/movies/{id}")
    suspend fun getMovieDetails(@Path("id") id: String): Response<MovieDetailsDto>

}

suspend fun main() {
    val client = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IClientMovie::class.java)

    println(client.getMovieDetails("1").body())

}
