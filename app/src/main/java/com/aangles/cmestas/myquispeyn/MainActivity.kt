package com.aangles.cmestas.myquispeyn

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.aangles.cmestas.myquispeyn.components.BottomNavigationBar
import com.aangles.cmestas.myquispeyn.navigation.AppNavigation
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
import com.aangles.cmestas.myquispeyn.ui.theme.Laboratorio02Theme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
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
                    FirebaseMessaging.getInstance().token
                        .addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Log.d("FCM Notify", "Fetching FCM registration token failed", task.exception)
                                return@OnCompleteListener
                            }

                            //Get new FCM registration token
                            val token: String? = task.result
                            Log.d("FCM Token", token, task.exception)
                            //Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
                        })

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