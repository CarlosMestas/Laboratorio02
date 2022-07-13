package com.aangles.cmestas.myquispeyn.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.dataStore.UserManager
import com.aangles.cmestas.myquispeyn.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun PreferencesScreen(navController: NavController){
    Scaffold {
        Column {
            TopAppBar( ){
                Text(text = "Preferencias de usuario")
            }
            val context = LocalContext.current
            // a coroutine scope
            val scope = rememberCoroutineScope()

            MyComponent2(navController, context, scope)
        }
    }
}

@Composable
fun MyComponent2(navController: NavController, context: Context, scope: CoroutineScope){
    Column (modifier = Modifier
        .padding(10.dp))
    {
        Box(modifier = Modifier
            .height(25.dp)
            .fillMaxWidth()
        ) {
            Image(
                painter =  painterResource(id = R.drawable.ic_parking_svgrepo_com),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Inside,
                contentDescription = null,
            )
        }
        Formulario(navController, Modifier.padding(dimensionResource(R.dimen.padding_medium)), context, scope)

    }
}

@Composable
fun Formulario(navController: NavController, modifier: Modifier = Modifier, context: Context, scope: CoroutineScope){
    Box(modifier = Modifier
        .height(550.dp)
        .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier
                .height(550.dp)
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
                    "Seleccione e ingrese sus \npreferencias: ",
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Text(
                    "preferencias: ",
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(12.dp))

                var nicknamePref by remember { mutableStateOf("") }
                val nickNameActual = userManager.nicknameFlow.collectAsState(initial = 0).value.toString()!!
                Text(
                    "Apodo : $nickNameActual",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                OutlinedTextField (
                    value = nicknamePref,
                    onValueChange = { nicknamePref = it },
                    label = {
                        Text("Nuevo apodo del usuario")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                var gender: String
                val genderActual = userManager.genderFlow.collectAsState(initial = 0).value
                if (genderActual == true)
                    gender = "masculino"
                else
                    gender = "femenino"
                var genderPref = remember { mutableStateOf(true) }
                Text(
                    "Sexo : $gender",
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Text(
                    "Masculino",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Switch(
                    checked = genderPref.value,
                    onCheckedChange = { newState -> genderPref.value = newState }
                )

                Button(onClick = {
                    scope.launch {
                        userManager.storeNickName(nicknamePref)
                        userManager.storeGender(genderPref.value)
                    }
                    navController.navigate(route = AppScreens.HomeScreen.route)
                }){
                    Text("Aceptar",    style = MaterialTheme.typography.button)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Información del uso de la",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                Text(
                    "aplicación:",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1
                )
                val a = userManager.quantityofinteractionsregions.collectAsState(initial = 0).value
                val a2 = userManager.quantityofinteractionsgps.collectAsState(initial = 0).value

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Cantidad de veces que se ingresó a una región: $a",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    "Cantidad de veces que se ingresó a un estacionamiento: $a2",
                    fontSize = 15.sp,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                )

            }
        }
    }
}