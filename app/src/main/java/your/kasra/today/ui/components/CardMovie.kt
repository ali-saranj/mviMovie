package your.kasra.today.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import your.kasra.today.R
import your.kasra.today.data.model.Movie
import your.kasra.today.ui.theme.MyApplicationTheme

@Composable
fun CardMovie(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (id: Int) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick(movie.id) },
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            modifier = Modifier.size(width = 140.dp, height = 160.dp),
            model = movie.poster,
            contentScale = ContentScale.Crop,
            contentDescription = movie.title,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(1.0f),
            text = movie.title,
            color = Color.White,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = movie.imdbRating!!,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.size(8.dp))
            StarRatingBar(rating = movie.imdbRating.toFloat()) {
            }
        }
    }
}

@Preview(
    name = "CardNews",
    showBackground = true,
    backgroundColor = android.graphics.Color.BLACK.toLong()
)
@Composable
private fun PreviewCardMovies() {
    MyApplicationTheme {
        CardMovie(

            movie = Movie(
                country = "USA",
                genres = listOf("Action", "Drama"),
                id = 1,
                images = listOf("https://www.gstatic.com/tv/thumb/movieposters/19275533/p19275533_p_v8_aa.jpg"),
                imdbRating = "9.5",
                poster = "https://www.gstatic.com/tv/thumb/movieposters/19275533/p19275533_p_v8_aa.jpg",
                title = "The Batman",
                year = "2022",
                actors = null,
                description = null,
                runtime = null,
                released = null,
                imdbVotes = null,
                director = null
            ),

            onClick = {}

        )
    }
}