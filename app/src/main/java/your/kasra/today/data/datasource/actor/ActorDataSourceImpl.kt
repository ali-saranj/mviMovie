package your.kasra.today.data.datasource.actor

import your.kasra.today.data.mapers.toActor
import your.kasra.today.data.mapers.toMovie
import your.kasra.today.data.model.Actor
import your.kasra.today.data.remote.IClientActor
import your.kasra.today.utils.Resource
import javax.inject.Inject

class ActorDataSourceImpl @Inject constructor(private val iclient: IClientActor) : ActorDataSource {
    override suspend fun getAllActors(name: String): Resource<Actor> {
        try {
            val response = iclient.search(name)

            return if (response.isSuccessful) {
                Resource.Success(response.body()!!.results[0].toActor())
            } else {
                Resource.Error(response.message())
            }
        } catch (exception: Exception) {
            return Resource.Error(exception.message ?: "Something went wrong")
        }
    }
}