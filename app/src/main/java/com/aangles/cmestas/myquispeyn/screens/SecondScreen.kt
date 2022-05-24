package com.aangles.cmestas.myquispeyn.screens

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

@Composable
fun SecondScreen(navController: NavController, text: String?) {
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
        SecondBodyContent(navController, text)
    }
}
@Composable
fun SecondBodyContent(navController: NavController, text: String?){
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
            MyMessages(myList, navController );}
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
fun MyMessages(_messages: List<MyMessage>, navController: NavController){
    LazyColumn(){
        items(_messages){ message ->
            MyComponent(message, navController)
        }
    }
}

@Composable
fun MyComponent(_message: MyMessage, navController: NavController){
    Column (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)){
        //MyImage(_message, navController)
    }
    Row(modifier = Modifier
        .padding(10.dp)){
        MyTexts(_message)
        BottonMapa(navController)
    }
}

@Composable
fun BottonMapa(navController: NavController){
    Box(modifier = Modifier
        .height(50.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_direction_gps_location),
                contentDescription = "Ir mapa"
            )
        }
    }
}

@Composable
fun MyTexts(_message: MyMessage){
    var expanded by remember{ mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(start = 7.dp)
            .width(300.dp)
            .clickable {
                expanded = !expanded
            }) {
        MyText(
            _message.title,
            MaterialTheme.colors.primaryVariant,
            MaterialTheme.typography.subtitle1
        )
        Spacer(modifier = Modifier.height(10.dp))
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