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
fun FirstScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(){
                Text(text = "Primera pantalla")
            }
        }
    ) {
        MediaList(navController)
    }
}

//@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaList(navController: NavController) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(2.dp),
        cells = GridCells.Adaptive(150.dp)
    ) {
        items(getItem()) { item ->
            CajaListItem(navController, item, Modifier.padding(dimensionResource(R.dimen.padding_medium)))
        }
    }
}

@Composable
fun CajaListItem(navController: NavController,item: CajaItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable {
            navController.navigate(route = AppScreens.SecondScreen.route + "/${item.name}")
        },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = modifier
        ){
            Box(modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(
                        data= item.thumb
                    ),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .padding(16.dp)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }

}
