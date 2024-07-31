package your.kasra.today.ui.screens.listnewsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import your.kasra.today.data.model.Movie
import your.kasra.today.ui.components.CardMovie

@Composable
fun ListMoviesScreen(
    modifier: Modifier = Modifier,
    viewmodel: ListMoviesViewModel = hiltViewModel()
) {
    val state by viewmodel.state.collectAsState()
    LaunchedEffect(key1 = viewmodel) {
        viewmodel.handleIntent(ListMoviesIntent.LoadMovies)
    }
    when {
        state.isError -> {
            ErrorContent(modifier, state.errorMessage ?: "error")
        }

        state.isLoading -> {
            LoadingContent(modifier)
        }

        state.isSuccess -> {
            ListMoviesContent(modifier, state.movieList!!, onclick = { viewmodel.clickMovie(it) })
        }
    }

}

@Preview(name = "ListNewsScreen")
@Composable
private fun PreviewListNewsScreen() {
    ListMoviesScreen()
}

@Composable
fun ListMoviesContent(
    modifier: Modifier = Modifier,
    listMovies: List<Movie>,
    onclick: (id: Int) -> Unit = {},
    onLoading: (loading: Boolean) -> Unit = {}
) {
    var loading by remember { mutableStateOf(false) }

    LazyVerticalGrid(
        modifier = Modifier.background(color = Color(0xFF151C25)),
        columns = GridCells.Fixed(3),
        content = {
            items(listMovies) { movie ->
                if (movie.id == listMovies.last().id) {
                    loading = true
                } else {
                    CardMovie(
                        modifier = Modifier.padding(8.dp),
                        movie = movie,
                        onClick = { onclick(it) })
                    loading = false
                }
            }
            item {
                if (loading) {
                    CircularProgressIndicator() // Show loading indicator
                }
            }
        })

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
