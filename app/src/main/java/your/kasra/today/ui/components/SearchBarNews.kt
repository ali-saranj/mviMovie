package your.kasra.today.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import retrofit2.http.Query

@Composable
fun SearchBarNews(
    modifier: Modifier = Modifier,
    query: String = "",
    onValueChange: (String) -> Unit = {}
) {
    var value by remember { mutableStateOf(query) }
    TextField(
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        value = value,
        onValueChange = {
            value = it
            onValueChange(it)
        },
        placeholder = { Text("Search News") },
        shape = MaterialTheme.shapes.large,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search, contentDescription = "search"
            )
        }
    )
}

@Preview(name = "SearchBarNews")
@Composable
private fun PreviewSearchBarNews() {
    SearchBarNews()
}