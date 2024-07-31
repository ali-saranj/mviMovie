package your.kasra.today.data.remote

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import your.kasra.today.data.remote.model.getactor.ApiResultActor
import your.kasra.today.utils.Constant

interface IClientActor {
    @GET("search/person?api_key=${Constant.API_KEY_TMDB}")
    suspend fun search(@Query("query") actor_name: String): Response<ApiResultActor>
}

suspend fun main() {
    val client = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL_TMDB)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IClientActor::class.java)

    println(client.search("Noel Appleby").body()!!.results[0].profilePath)

}