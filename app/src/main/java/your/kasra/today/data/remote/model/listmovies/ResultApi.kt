package your.kasra.today.data.remote.model.listmovies


import com.google.gson.annotations.SerializedName

data class ResultApi(
    @SerializedName("data")
    val `data`: List<MovieDto>,
    @SerializedName("metadata")
    val metadata: Metadata
)