package com.aangles.cmestas.myquispeyn.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.aangles.cmestas.myquispeyn.navigation.AppScreens

@Composable
fun SecondScreen(
    navController: NavController,
    text: String?,
    id: String?,
    state: SecondScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
) {
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
                Text(text = "Lista de Parqueos")
            }
        }
    ) {
        SecondBodyContent(navController, text, id, state, isRefreshing, refreshData)
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
    refreshData: () -> Unit
){
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        text?.let{ Text("Parqueos en la Ciudad de \n" + it,
            fontSize = 25.sp,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center,
            maxLines = 2,
        )}
    }
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(){
                MyMessages(navController, state, id, isRefreshing, refreshData)
            Row(){
                Button(onClick = {
                    navController.popBackStack()
                }){
                    Text("Volver")
                }
            }
        }
    }
}
//            MyMessages(navController, state, id, isRefreshing, refreshData)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyMessages(
    navController: NavController,
    state: SecondScreenState,
    id: String?,
    isRefreshing: Boolean,
    refreshData: () -> Unit
){
    LazyColumn(){
        items(
            items = state.carparks
        ){ item->
            if (item != null) {
                if(id.equals(item.regionId))
                    MyComponent(item, navController)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyComponent(_message: CarPark, navController: NavController){
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
        //MyImage(_message, navController)
    }
    Row(modifier = Modifier
        .padding(20.dp))
    //    .background(MaterialTheme.colors.background))
    {
        //MyImage(_message, navController)
        MyTexts(_message)
        BottonMapa(_message = _message, navController = navController)
    }
}

@Composable
fun BottonMapa(_message: CarPark, navController: NavController){
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = {
                navController.navigate(route = AppScreens.ThirdScreen.route
                        + "/${_message.name}"
                        + "/${_message.address}"
                        + "/${_message.dateO}"
                        + "/${_message.dateC}"
                        + "/${_message.lat}"
                        + "/${_message.lon}")
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

/*
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTexts(_message: CarPark, navController: NavController){
    var expanded by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 7.dp)
            .clickable {
            // Expansión del mensaje
            //                expanded = !expanded
                navController.navigate(route = AppScreens.ThirdScreen.route
                        + "/${_message.name}"
                        + "/${_message.address}"
                        + "/${_message.dateO}"
                        + "/${_message.dateC}"
                        + "/${_message.lat}"
                        + "/${_message.lon}")

            }) {
        _message.address?.let {
            MyText(
                it,
                MaterialTheme.colors.primary,
                MaterialTheme.typography.subtitle1
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        _message.toString()?.let {
            MyText(
                it,
                MaterialTheme.colors.onBackground,
                MaterialTheme.typography.subtitle2,
                if (expanded) Int.MAX_VALUE else 1
            )
        }
    }
}
*/


@Composable
fun MyText(_text: String, _color: Color, _style: TextStyle, _lines: Int = Int.MAX_VALUE){
    Text(_text, color = _color, style = _style, maxLines = _lines)
}





/*

package com.aangles.cmestas.myquispeyn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.navigation.AppScreens

@Composable
fun SecondScreen(
    navController: NavController,
    text: String?,
    state: SecondScreenState,
    isRefreshing: Boolean,
    refreshData: ()->Unit
) {
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
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Segunda pantalla")
            }
        }
    ) {
        SecondBodyContent(navController, text, state, isRefreshing, refreshData)
    }
}
@Composable
fun SecondBodyContent(
    navController: NavController,
    text: String?,
    state: SecondScreenState,
    isRefreshing: Boolean,
    refreshData: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Parqueos en la Ciudad de",
            style = MaterialTheme.typography.h5
        )
        text?.let{
            Text(it,
                style = MaterialTheme.typography.h5)
        }

        Row(){
            MyMessages(myList, navController, state, isRefreshing, refreshData);}
        Row(){
            Button(onClick = {
                navController.popBackStack()
            }){
                Text("Volver")
            }
        }
    }
}

data class MyMessage(val title: String, val body: String)

@Composable
fun MyMessages(
    _messages: List<MyMessage>,
    navController: NavController,
    state: SecondScreenState,
    isRefreshing: Boolean,
    refreshData: () -> Unit
){
    LazyColumn(){
        //////////////////////////////////////////////////////////////////////////////////////
        items(_messages){ message ->
            MyComponent(message, navController)
        }
        ///////////
        items(
            items = state.carparks
        ){ item->
            if (item != null) {
                MyComponent(item, navController)
            }
        }
    }
}

@Composable
fun MyComponent(_message: MyMessage, navController: NavController){
    Column (modifier = Modifier
        .padding(10.dp)){
        //MyImage(_message, navController)
    }
    Row(modifier = Modifier
        .padding(20.dp)
        .background(MaterialTheme.colors.background)){
        //MyImage(_message, navController)
        MyTexts(_message, navController)
    }
}


@Composable
fun MyTexts(_message: MyMessage, navController: NavController){
    var expanded by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 7.dp)
            .clickable {
            // Expansión del mensaje
            //                expanded = !expanded
                navController.navigate(route = AppScreens.ThirdScreen.route + "/holi")

            }) {
        MyText(
            _message.title,
            MaterialTheme.colors.primary,
            MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(15.dp))
        MyText(
            _message.body,
            MaterialTheme.colors.onBackground,
            MaterialTheme.typography.subtitle2,
            if (expanded) Int.MAX_VALUE else 1
        )
    }
}

@Composable
fun MyText(_text: String, _color: Color, _style: TextStyle, _lines: Int = Int.MAX_VALUE){
    Text(_text, color = _color, style = _style, maxLines = _lines)
}




private val myList: List<MyMessage> =
    listOf(
        MyMessage("PARQUEOS AQP - Valet Parking","Abierto\n" +
                "C. Deán Valdivia 328\n" +
                "Cierra a las 21:15"),
        MyMessage("Playa de Estacionamiento LA MERCED Parqueo vehicular","Abierto\n" +
                "Cercado De Arequipa · 979 350 745\n" +
                "Cierra a las 20:30"),
        MyMessage("Terrapuerto Parqueo Exterior","Abierto\n" +
                "Jacobo Hunter"),
        MyMessage("Estacionamiento Los Portales","Cerrado\n" +
                "C. Rivero 207 · (01) 2114466\n" +
                "Abre pronto: ⋅ 09:00"),

        )

*
* */