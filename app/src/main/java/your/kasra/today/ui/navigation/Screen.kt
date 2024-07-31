package your.kasra.today.ui.navigation

sealed class Screen (val route: String){
    data object ListNewsScreen : Screen("listNewsScreen")
    data object DetailScreen : Screen("detailScreen")
}