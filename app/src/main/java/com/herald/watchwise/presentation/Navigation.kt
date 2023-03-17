package com.herald.watchwise.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.herald.watchwise.presentation.screens.screen_main.MainScreen
import com.herald.watchwise.presentation.screens.screen_movie.MovieScreen
import com.herald.watchwise.presentation.screens.screen_saved.SavedScreen
/**
 * the navigation function that has
 * the three screens of our app
 */
@Composable
fun Navigation() {
    val navController = rememberNavController()
     NavHost(navController = navController , startDestination = Screens.MainScreen.route  ){
         composable(Screens.MainScreen.route){
             MainScreen(navController = navController)
         }
         composable(
             Screens.MovieScreen.route + "/{movieID}",
             arguments = listOf(navArgument("movieID"){
                 type = NavType.IntType
             })
         ){
             MovieScreen(navController = navController, movieID = it.arguments!!.getInt("movieID"))
         }
         composable(Screens.SavedScreen.route){
             SavedScreen(navController)
         }
     }
}