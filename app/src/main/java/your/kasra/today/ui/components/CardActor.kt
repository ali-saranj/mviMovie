package your.kasra.today.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import your.kasra.today.R
import your.kasra.today.data.model.Actor
import your.kasra.today.data.model.Movie
import your.kasra.today.ui.theme.MyApplicationTheme

@Composable
fun CardActor(
    modifier: Modifier = Modifier,
    actor: Actor,
) {
    Box(modifier = Modifier.size(width = 140.dp, height = 180.dp)) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            model = actor.image,
            contentDescription = actor.name,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
        ) {

            Text(text = actor.name,color=Color.White)
            Text(text = "You Like 3 Movies", fontSize = 8.sp, color = Color(0xFF828DA3))
        }

    }


}

@Preview(name = "CardActor")
@Composable
private fun PreviewCardActor() {
    MyApplicationTheme {
        CardActor(
            actor = Actor(
                name = "eddie redmayne",
                image = "https://www.gstatic.com/tv/thumb/movieposters/19275533/p19275533_p_v8_aa.jpg"
            )
        )
    }
}

@Composable
private fun PreviewCardMovies() {
    MyApplicationTheme {
        Actor(
            name = "eddie redmayne",
            image = "https://www.gstatic.com/tv/thumb/movieposters/19275533/p19275533_p_v8_aa.jpg"
        )
    }
}