package com.aangles.cmestas.myquispeyn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val myList: List<MyMessage> =
    listOf(
        MyMessage("Hola mundo","Holi boli crayoli"),
        MyMessage("Hola mundo 2","Holi boli crayoli Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet risus neque. Phasellus euismod nunc sed magna sollicitudin, et auctor augue porttitor. Pellentesque sit amet sodales turpis. Nam bibendum sapien quis fermentum imperdiet. Nam molestie quis tellus a suscipit. Duis eget tortor lacus. Nulla facilisi. "),
        MyMessage("Hola mundo 3","Holi boli crayoli Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet risus neque."),
        MyMessage("Hola mundo 4","Holi boli crayoli  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet risus neque. Phasellus euismod nunc sed magna sollicitudin, et auctor augue porttitor. Pellentesque sit amet sodales turpis. Nam bibendum sapien quis fermentum imperdiet. Nam molestie quis tellus a suscipit. Duis eget tortor lacus. Nulla facilisi.\n" +
                "\n" +
                "Nunc luctus pellentesque porttitor. Pellentesque quis mollis nisi. Nunc sed eros non diam mattis posuere. Duis scelerisque viverra suscipit. Pellentesque leo nulla, eleifend at dignissim ut, pharetra vel diam. Phasellus rutrum arcu ut luctus pellentesque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis gravida suscipit tortor vel luctus. Nulla eu sapien nibh. Praesent ac pellentesque urna. Nulla facilisi. Suspendisse faucibus porta sem, ac fringilla tellus fringilla id.\n" +
                "\n" +
                "Cras tempus magna sit amet euismod auctor. Aliquam sit amet ligula molestie, tristique magna aliquam, sodales tortor. Pellentesque sollicitudin velit elit, eget tincidunt ligula dapibus eu. Nam et nibh massa. Donec urna eros, ullamcorper ut congue quis, ultricies pellentesque purus. Aliquam vel placerat dolor. Cras molestie fermentum dolor nec consequat. Suspendisse faucibus massa non magna fermentum, sed dignissim ex facilisis. Etiam placerat egestas nibh, sit amet dictum nisi pharetra vel. Vivamus justo neque, aliquet id condimentum sed, semper a metus. Nunc ac velit quis urna luctus aliquam ut pulvinar velit. Fusce nec libero ac felis suscipit auctor sit amet rutrum enim. Vestibulum vel mi ut purus ullamcorper congue sed vel ex. Aliquam id lorem non metus consequat hendrerit a eu risus. "),
        MyMessage("Hola mundo 5","Holi boli crayoli"),
        MyMessage("Hola mundo 6","Holi boli crayoli Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet risus neque."),
        MyMessage("Hola mundo 7","Holi boli crayoli Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus sit amet risus neque. Phasellus euismod nunc sed magna sollicitudin, et auctor augue porttitor. Pellentesque sit amet sodales turpis. Nam bibendum sapien quis fermentum imperdiet. Nam molestie quis tellus a suscipit. Duis eget tortor lacus. Nulla facilisi.\n" +
                "\n" +
                "Nunc luctus pellentesque porttitor. Pellentesque quis mollis nisi. Nunc sed eros non diam mattis posuere. Duis scelerisque viverra suscipit. Pellentesque leo nulla, eleifend at dignissim ut, pharetra vel diam. Phasellus rutrum arcu ut luctus pellentesque. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis gravida suscipit tortor vel luctus. Nulla eu sapien nibh. Praesent ac pellentesque urna. Nulla facilisi. Suspendisse faucibus porta sem, ac fringilla tellus fringilla id. "),
        MyMessage("Hola mundo 8","Holi boli crayoli"),
        MyMessage("Hola mundo 9","Holi boli crayoli")
    )

@Composable
fun FirstScreen(navController: NavController){
    Scaffold {
        Column(){
            TopAppBar(){
                Text(text = "Primera pantalla")
            }
            BodyContent(navController)
        }
    }
}

@Composable
fun BodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ){
            MyMessages(myList, navController)
        /*
        Text("Hola para navegar")
        Button(onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route + "/Este es un parámetro")
        }){
            Text("Navega")

        }
        */

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
    Row(modifier = Modifier
        .padding(7.dp)
        .background(MaterialTheme.colors.background)){
        MyImage(_message, navController)
        MyTexts(_message)
    }
}

@Composable
fun MyImage(_message: MyMessage, navController: NavController){
    Image(
        painterResource(id = R.drawable.ic_launcher_foreground),
        "Imagen de prueba",
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
            .size(64.dp)
            .clickable {
                navController.navigate(route = AppScreens.SecondScreen.route + "/${_message.title}")
            }
    )
}

@Composable
fun MyTexts(_message: MyMessage){
    var expanded by remember{mutableStateOf(false)}
    Column(
        modifier = Modifier
            .padding(start = 7.dp)
            .clickable {
                expanded = !expanded
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

