package your.kasra.today.data.remote.model.listmovies


import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("genres")
    val genres: List<String>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("imdb_rating")
    val imdbRating: String,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)