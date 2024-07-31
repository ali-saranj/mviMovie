package your.kasra.today.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun CardActor(
    modifier: Modifier = Modifier
) {

        Box(modifier = Modifier){
//            AsyncImage(model = , contentDescription = , imageLoader = )
        }

}

@Preview(name = "CardActor")
@Composable
private fun PreviewCardActor() {
    CardActor()
}