package com.aangles.cmestas.myquispeyn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.aangles.cmestas.myquispeyn.components.BottomNavigationBar
//import com.aangles.cmestas.myquispeyn.clases.addItem
import com.aangles.cmestas.myquispeyn.navigation.AppNavigation
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
//import com.aangles.cmestas.myquispeyn.screens.database
import com.aangles.cmestas.myquispeyn.ui.theme.Laboratorio02Theme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio02Theme(){
                Surface(color = MaterialTheme.colors.background){
                    MainHomeScreen()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainHomeScreen() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        AppScreens.HomeScreen,
        AppScreens.FirstScreen,
        AppScreens.HistorialScreen
    )
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) }
    ) {
        AppNavigation(navController)
    }
}