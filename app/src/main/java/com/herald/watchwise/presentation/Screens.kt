package com.herald.watchwise.presentation

/**
 * a sealed class with our screens
 * and it's used in our navigation
 * and it makes our code more readable
 */
sealed class Screens(val route:String){
    object MainScreen  : Screens("Main_Screen")
    object MovieScreen : Screens("Movie_Screen")
    object SavedScreen : Screens("Saved_Screen")
}
