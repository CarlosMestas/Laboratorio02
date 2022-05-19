package com.aangles.cmestas.myquispeyn.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberImagePainter
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.clases.CajaItem
import com.aangles.cmestas.myquispeyn.clases.getItem
import com.aangles.cmestas.myquispeyn.navigation.AppScreens


@Composable
fun HomeScreen(navController: NavController){
    Scaffold {
        Column {
            TopAppBar(){
                Text(text = "Bienvenido")
            }
            //MyComponent()
        }
    }
}

@Composable
fun MyComponent(){
    Column (modifier = Modifier
        .padding(10.dp)){

    }
    Row(modifier = Modifier
        .padding(20.dp)
        .background(MaterialTheme.colors.background)){

    }
}
