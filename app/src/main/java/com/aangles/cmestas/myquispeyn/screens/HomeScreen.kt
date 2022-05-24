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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.clases.CajaItem
import com.aangles.cmestas.myquispeyn.clases.getItem
import com.aangles.cmestas.myquispeyn.navigation.AppScreens


@Composable
fun HomeScreen(navController: NavController){
    Scaffold {
        Column {
            TopAppBar( ){
                Text(text = "Bienvenido")
            }
            MyComponent(navController = navController)
        }
    }
}

@Composable
fun MyComponent(navController: NavController){
    Column (modifier = Modifier
        .padding(10.dp))
    {
        Box(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
        ) {
            Image(
                painter =  painterResource(id = R.drawable.ic_parking_svgrepo_com),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside,
                contentDescription = null,
                )
        }
        DatosApp(navController, Modifier.padding(dimensionResource(R.dimen.padding_medium)))

    }
}

@Composable
fun DatosApp(navController: NavController, modifier: Modifier = Modifier){
    Box(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier
                .height(200.dp)
                .width(270.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "FOUND PARKING",
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Busca los lugares de parqueo en cualquier región del Perú",
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 4,
                    modifier = Modifier.padding(12.dp,0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = {
                    navController.navigate(route = AppScreens.FirstScreen.route)
                }){
                    Text("Ver Regiones",    style = MaterialTheme.typography.button)
                }
            }
        }
    }
    DatosIntegrantes(Modifier.padding(dimensionResource(R.dimen.padding_medium)))
}

@Composable
fun DatosIntegrantes(modifier: Modifier = Modifier){
    Box(modifier = Modifier
        .height(150.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier
                .height(150.dp)
                .width(270.dp),
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Integrantes: ",
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Angles Quiroz, Ana Lucia",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(12.dp,0.dp)
                )
                Text(
                    "Mestas Escarcena, Carlos Alberto",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(12.dp,0.dp)
                )
                Text(
                    "Quispe Yncarroque, Maribel Araceli",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(12.dp,0.dp)
                )

            }
        }
    }
}
