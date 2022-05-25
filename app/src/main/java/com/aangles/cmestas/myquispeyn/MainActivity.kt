package com.aangles.cmestas.myquispeyn

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.aangles.cmestas.myquispeyn.clases.MyItem
import com.aangles.cmestas.myquispeyn.components.BottomNavigationBar
//import com.aangles.cmestas.myquispeyn.clases.addItem
import com.aangles.cmestas.myquispeyn.navigation.AppNavigation
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
//import com.aangles.cmestas.myquispeyn.screens.database
import com.aangles.cmestas.myquispeyn.ui.theme.Laboratorio02Theme
import com.google.firebase.database.*
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
    )
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) }
    ) {
        AppNavigation(navController)
    }
}