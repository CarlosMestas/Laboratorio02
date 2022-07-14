package com.aangles.cmestas.myquispeyn.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route:String,
    val title: String,
    val icon: ImageVector
    ){
    object HomeScreen: AppScreens("home_screen", "Inicio", Icons.Filled.Home)
    object FirstScreen: AppScreens("first_screen", "Ver por regiones", Icons.Filled.Search)
    object SecondScreen: AppScreens("second_screen","Parqueos disponibles", Icons.Filled.List)
    object ThirdScreen: AppScreens("third_screen","Parqueo en el mapa", Icons.Filled.List)
    object PreferencesScreen: AppScreens("fourth_screen","Preferencias del usuario", Icons.Filled.List)
    object HistorialScreen: AppScreens("fifth_screen", "Historial", Icons.Outlined.List)
    object EditCarParkScreen: AppScreens("edit2?carParkId={carParkId}", "Editar parqueo", Icons.Filled.Edit){
        fun passId(carParkId: Int?): String {
            return "edit2?carParkId=$carParkId"
        }
    }
}