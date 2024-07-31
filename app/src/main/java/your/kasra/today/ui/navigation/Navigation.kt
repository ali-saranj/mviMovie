/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package your.kasra.today.ui.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import your.kasra.today.ui.screens.detailscreen.DetailScreen
import your.kasra.today.ui.screens.listnewsscreen.ListMoviesScreen

@Composable
fun MainNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.ListNewsScreen.route) {
        composable(Screen.ListNewsScreen.route, exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(100)
            )
        }, enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(100)
            )
        }) { ListMoviesScreen() }


        composable(route = Screen.DetailScreen.route+"?movieId={movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType }),
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(100)
                )
            }, enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(100)
                )
            }
        ) {  backStackEntry ->
            Log.e("MainNavigation: ", backStackEntry.arguments?.getString("movieId")!!)
            DetailScreen( id = backStackEntry.arguments?.getString("movieId")!!)
        }
    }
}
