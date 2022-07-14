package com.aangles.cmestas.myquispeyn.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.room.Database
import androidx.room.Room
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.aangles.cmestas.myquispeyn.data.local.DataBaseDB
import com.aangles.cmestas.myquispeyn.data.local.dao.CarParkDBDao
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction0

private lateinit var dao: CarParkDBDao
private lateinit var db: DataBaseDB

@Composable
fun SecondScreen(
    navController: NavController,
    text: String?,
    id: String?,
    state: SecondScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
) {
    val context = LocalContext.current
    // a coroutine scope
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar() {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Text(text = "Volver")
            }
        }
    ) {
        SecondBodyContent(navController, text, id, state, isRefreshing, refreshData, context, scope)
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SecondBodyContent(
    navController: NavController,
    text: String?,
    id: String?,
    state: SecondScreenState,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    context: Context,
    scope: CoroutineScope
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        text?.let{ Text("Parqueos en la Ciudad de \n" + it,
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )}
        LazyColumn(
            contentPadding = PaddingValues(2.dp)
        ) {
            items(
                items = state.carparks
            ){ item->
                if (item != null) {
                    if(id.equals(item.regionId))
                        MyComponent(item, navController, context, scope)
                }
            }
        }

    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyMessages(
    navController: NavController,
    state: SecondScreenState,
    id: String?,
    isRefreshing: Boolean,
    refreshData: () -> Unit,
    context: Context,
    scope: CoroutineScope
){
    LazyColumn(){
        items(
            items = state.carparks
        ){ item->
            if (item != null) {
                if(id.equals(item.regionId))
                    MyComponent(item, navController, context, scope)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyComponent(_message: CarPark, navController: NavController,     context: Context,
                scope: CoroutineScope){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
    }
    Row(modifier = Modifier
        .padding(10.dp))
    {
        MyTexts(_message)
        BottonMapa(_message = _message, navController = navController, context, scope)
    }
}

@Composable
fun BottonMapa(_message: CarPark, navController: NavController,     context: Context,
               scope: CoroutineScope){
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val q2 =  userManager.quantityofinteractionsgps.collectAsState(initial = 0).value
        IconButton(
            onClick = {
                navController.navigate(route = AppScreens.ThirdScreen.route
                        + "/${_message.name}"
                        + "/${_message.address}"
                        + "/${_message.dateO}"
                        + "/${_message.dateC}"
                        + "/${_message.lat}"
                        + "/${_message.lon}")
                scope.launch {
                    userManager.storeQuantityOfInteractionsGps(q2 + 1)
                }
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_direction_gps_location),
                contentDescription = "Ir mapa"
            )
        }
    }
}

@Composable
fun MyTexts(_message: CarPark){
    var expanded by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 7.dp)
            .width(300.dp)
            .clickable {
                expanded = !expanded
            }) {
        _message.name?.let {
            MyText(
                it,
                MaterialTheme.colors.primaryVariant,
                MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        _message.address?.let {
            MyText(
                it,
                MaterialTheme.colors.onBackground,
                MaterialTheme.typography.subtitle2,
                if (expanded) Int.MAX_VALUE else 1
            )
        }
    }
}

@Composable
fun MyText(_text: String, _color: Color, _style: TextStyle, _lines: Int = Int.MAX_VALUE){
    Text(_text, color = _color, style = _style, maxLines = _lines)
}