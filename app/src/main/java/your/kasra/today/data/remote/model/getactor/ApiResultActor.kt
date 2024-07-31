package your.kasra.today.data.remote.model.getactor


import com.google.gson.annotations.SerializedName

data class ApiResultActor(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ActorDto>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)