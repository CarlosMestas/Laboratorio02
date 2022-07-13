package com.aangles.cmestas.myquispeyn.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aangles.cmestas.myquispeyn.screens.FirstScreen
import com.aangles.cmestas.myquispeyn.screens.HomeScreen
import com.aangles.cmestas.myquispeyn.screens.SecondScreen
import com.aangles.cmestas.myquispeyn.screens.ThirdScreen
import com.aangles.cmestas.myquispeyn.screens.*

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
            val viewModel: SecondScreenViewModel = hiltViewModel()
            val state = viewModel.state.value
            val isRefreshing = viewModel.isRefreshing.collectAsState()
            val textA = it.arguments?.getString("text")
            val id = it.arguments?.getString("id")

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

