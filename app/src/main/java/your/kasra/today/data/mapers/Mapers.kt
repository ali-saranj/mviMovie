package your.kasra.today.data.mapers

import your.kasra.today.data.model.Actor
import your.kasra.today.data.model.Movie
import your.kasra.today.data.remote.model.getactor.ActorDto
import your.kasra.today.data.remote.model.listmovies.MovieDto
import your.kasra.today.data.remote.model.moviedetail.MovieDetailsDto
import your.kasra.today.utils.Constant

fun MovieDto.toMovie(): Movie {
    return Movie(
        country = country,
        genres = genres,
        id = id,
        images = images,
        imdbRating = imdbRating,
        poster = poster,
        title = title,
        year = year,
        actors = null,
        description = null,
        runtime = null,
        released = null,
        imdbVotes = null,
        director = null,
        )
}

fun MovieDetailsDto.toMovie(): Movie{
    return Movie(
        country = country,
        genres = genres,
        id = id,
        images = images,
        imdbRating = imdbRating,
        poster = poster,
        title = title,
        year = year,
        actors = actors.split(","),
        description = plot,
        runtime = runtime,
        released = released,
        imdbVotes = imdbVotes,
        director = director,
    )

}

fun ActorDto.toActor(): Actor {
    return Actor(
        name = name,
        image = Constant.BASE_URL_TMDB_IMAGE+profilePath,
    )
}