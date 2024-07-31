package your.kasra.today.ui.screens.detailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import your.kasra.today.R
import your.kasra.today.data.model.Movie
import your.kasra.today.ui.components.StarRatingBar

@Composable
fun DetailScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewmodel: DetailMovieViewModel = hiltViewModel(),
) {
    val state by viewmodel.state.collectAsState()

    LaunchedEffect(key1 = viewmodel) {
        viewmodel.handlerIntent(MovieDetailIntent.LoadMovie(id.toInt()))
    }
    when {
        state.isError -> {
            ErrorContent(modifier, state.errorMessage ?: "error")
        }

        state.isLoading -> {
            LoadingContent(modifier)
        }

        state.isSuccess -> {
            MovieContent(modifier, state.movie, viewmodel::back)
        }

    }
}

@Composable
fun MovieContent(
    modifier: Modifier = Modifier, movie: Movie? = null,
    onBackClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF151C25))
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f),
                model = movie?.poster,
                contentScale = ContentScale.Crop,
                contentDescription = "title",
                placeholder = painterResource(id = R.drawable.img)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .align(Alignment.BottomStart)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color(0xFF151C25)
                            )
                        )
                    )
            ) {
            }
            IconButton(
                modifier = Modifier.padding(8.dp),
                onClick = { onBackClick() },
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFF151C25))
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Back ",
                    tint = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(colors = CardDefaults.cardColors(Color(0xFFF2BD12)), shape = CircleShape) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            text = movie!!.year!!,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            StarRatingBar(rating = movie!!.imdbRating!!.toFloat()) {

                            }
                            Text(text = "Votes ${movie!!.imdbVotes!!}", color = Color.White)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = movie!!.imdbRating!!,
                            color = Color.White,
                            fontSize = 40.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = movie!!.title,
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium
                )
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccessTime,
                        contentDescription = "time",
                        tint = Color(0xFFF2BD12)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = movie.runtime!!, color = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))

                    LazyRow {
                        items(movie!!.genres!!) {
                            Card(
                                modifier = Modifier.padding(4.dp),
                                colors = CardDefaults.cardColors(Color(0xFF2A313D)),
                                shape = CircleShape
                            ) {
                                Text(
                                    modifier = Modifier.padding(6.dp),
                                    text = it,
                                    color = Color.White
                                )
                            }

                        }
                    }

                }

            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "summery",
                color = Color(0xFF828DA3),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = movie!!.description!!,
                color = Color.White
            )
        }


        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "About Film",
                color = Color(0xFF828DA3),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Text(text = "orginal title", color = Color(0xFF828DA3))
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = movie!!.title, color = Color.White)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Text(text = "released", color = Color(0xFF828DA3))
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = movie!!.released!!, color = Color.White)
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                Text(text = "director", color = Color(0xFF828DA3))
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = movie!!.director!!, color = Color.White)
            }
        }

    }
}

@Composable
fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color(0xFFFF9800))
    }
}

@Composable
fun ErrorContent(modifier: Modifier = Modifier, error: String) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = error)
    }
}

@Preview(name = "DetailScreen", showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    MovieContent(
        movie = Movie(
            country = "USA",
            genres = listOf("Action", "Drama", "Thriller"),
            id = 1,
            images = listOf("image1", "image2", "image3"),
            imdbRating = "9.2",
            poster = "",
            title = "Title",
            runtime = "120",
            year = "2022",
            actors = listOf("Actor 1", "Actor 2", "Actor 3"),
            description = "Description",
            released = "2022-01-01",
            imdbVotes = "1000",
            director = "Director"
        ),
        onBackClick = {}
    )
}

