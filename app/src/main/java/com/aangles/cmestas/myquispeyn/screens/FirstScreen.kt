package com.aangles.cmestas.myquispeyn.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.clases.MyItem
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
import com.aangles.cmestas.myquispeyn.repositories.CarParkRepository
import kotlinx.coroutines.launch


@Composable
fun FirstScreen(
    navController: NavController,
    state: FirstScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit){

    Scaffold(
        topBar = {
            TopAppBar(){
                Text(text = "Primera pantalla")
            }
        }
    ) {
        MediaList(navController, state, isRefreshing, refreshData)
    }
}

//@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MediaList(navController: NavController, state: FirstScreenState, isRefreshing: Boolean, refreshData: ()->Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "REGIONES DEL PERÚ",
            fontSize = 25.sp,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle2,
        )
        LazyVerticalGrid(
            contentPadding = PaddingValues(2.dp),
            cells = GridCells.Adaptive(150.dp)
        ) {
            items(
                items = state.regions
            ) { item ->
                CajaListItem(
                    navController,
                    item,
                    Modifier.padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CajaListItem(navController: NavController, item: MyItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    // a coroutine scope
    val q1 =  userManager.quantityofinteractionsregions.collectAsState(initial = 0).value
    val scope = rememberCoroutineScope()
    Card(
        modifier = modifier.clickable {
            regionIdFinal = item.id.toString()
            navController.navigate(route = AppScreens.SecondScreen.route + "/${item.name}" + "/${item.id}")
            scope.launch {
                userManager.storeQuantityOfInteractionsRegions(q1 + 1)
            }
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
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Inside,
                    contentDescription = null,
                )
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .padding(16.dp, 5.dp)
            ) {
                item.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
