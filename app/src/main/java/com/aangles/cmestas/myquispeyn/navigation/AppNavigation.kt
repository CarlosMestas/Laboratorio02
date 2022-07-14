package com.aangles.cmestas.myquispeyn.navigation

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aangles.cmestas.myquispeyn.screens.regions.FirstScreen
import com.aangles.cmestas.myquispeyn.screens.HomeScreen
import com.aangles.cmestas.myquispeyn.screens.listParks.SecondScreen
import com.aangles.cmestas.myquispeyn.screens.map.ThirdScreen
import com.aangles.cmestas.myquispeyn.screens.historial.HistorialScreen
import com.aangles.cmestas.myquispeyn.screens.historial.edit.EditCommentScreen
import com.aangles.cmestas.myquispeyn.screens.userPreferences.PreferencesScreen
import com.aangles.cmestas.myquispeyn.screens.listParks.SecondScreenViewModel
import com.aangles.cmestas.myquispeyn.screens.regions.FirstScreenViewModel

@ExperimentalMaterialApi
@Composable
fun AppNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route){
        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
        composable(route = AppScreens.PreferencesScreen.route){
            PreferencesScreen(navController)
        }
        composable(route = AppScreens.HistorialScreen.route){
            HistorialScreen(navController)
        }
        composable(
            route = AppScreens.EditCarParkScreen.route,
            arguments = listOf(
                navArgument(
                    name = "carParkId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            EditCommentScreen(navController = navController)
        }
        composable(
            route = AppScreens.FirstScreen.route
        ){

            val viewModel: FirstScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()

            FirstScreen(navController, state, isRefreshing = isRefreshing.value, refreshData = viewModel::getRegionList)
        }
        composable(
            route = AppScreens.SecondScreen.route + "/{text}" + "/{id}",
            arguments = listOf(
                navArgument(name = "text"){
                    type = NavType.StringType
                }
            )
        ){
            val textA = it.arguments?.getString("text")
            val id = it.arguments?.getString("id")
            val viewModel: SecondScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            Log.d("TestAB", "hola $id")
            if (id != null) {
                viewModel.regionId2 = id
                Log.d("TestAA", "hola $id")
            }

            SecondScreen(navController, textA, id, state, isRefreshing = isRefreshing.value, refreshData = viewModel::getParkCarList)

        }
        composable(route = AppScreens.ThirdScreen.route + "/{name}" + "/{address}" + "/{dateO}" + "/{dateC}" + "/{lat}" + "/{lon}",
            arguments = listOf(navArgument(name = "name"){
                type = NavType.StringType
            }
            )){
            ThirdScreen(navController,
                it.arguments?.getString("name"),
                it.arguments?.getString("address"),
                it.arguments?.getString("dateO"),
                it.arguments?.getString("dateC"),
                it.arguments?.getString("lat"),
                it.arguments?.getString("lon"))
        }
    }
}
