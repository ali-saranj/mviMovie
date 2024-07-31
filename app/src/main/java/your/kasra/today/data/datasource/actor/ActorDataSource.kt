package your.kasra.today.data.datasource.actor

import your.kasra.today.data.model.Actor
import your.kasra.today.utils.Resource

interface ActorDataSource {
    suspend fun getAllActors(name: String): Resource<Actor>
}