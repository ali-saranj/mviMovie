package your.kasra.today.ui.navigation

import android.annotation.SuppressLint
import android.util.Log
import androidx.navigation.NavHostController

class NavAction {
     var navigationController: NavHostController? = null

    fun navigateTodolistNewsScreen() {
        navigationController?.navigate(Screen.ListNewsScreen.route)
    }

    @SuppressLint("LongLogTag")
    fun navigateTodolistDetailScreen(movieId: Int) {
        navigationController?.navigate(Screen.DetailScreen.route+"?movieId=$movieId")
    }

    fun navigateBackMovieDetailScreen() {
        navigationController?.popBackStack()
    }

}