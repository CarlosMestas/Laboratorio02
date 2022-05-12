package com.aangles.cmestas.myquispeyn.navigation

sealed class AppScreens(val route:String){
    object FirstScreen: AppScreens("first_screen")
    object SecondScreen: AppScreens("second_screen")
}