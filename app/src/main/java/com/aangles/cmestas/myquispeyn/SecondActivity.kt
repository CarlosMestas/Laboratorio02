package com.aangles.cmestas.myquispeyn

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.aangles.cmestas.myquispeyn.screens.map.CustomInfoWindowForGoogleMap
import com.aangles.cmestas.myquispeyn.ui.theme.Laboratorio02Theme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.IllegalStateException

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio02Theme(){
                Surface(color = MaterialTheme.colors.background){
                   ThirdScreen(            )
                }
            }
        }
    }
}

@Composable
fun ThirdScreen(
) {
    Scaffold(
        topBar = {
            TopAppBar() {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow back"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Tercera pantalla")
            }
        }
    ) {
        ThirdBodyContent()
    }
}


@Composable
fun ThirdBodyContent(
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        MyMap(){}
    }

}

@Composable
fun MyMap(onReady:(GoogleMap) -> Unit){
    val context = LocalContext.current
    val mapView = remember{ MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(RememberMapLifecyCle(map = mapView))
    AndroidView(
        factory = {
            mapView.apply{
                mapView.getMapAsync{googleMap ->
                    val zoomLevel = 15f
                    val lat: Double = java.lang.Double.valueOf("-14.7945956")
                    val lon: Double = java.lang.Double.valueOf("-71.4103241")
                    val latlon = LatLng(lat, lon)
                    Toast.makeText(
                        context,
                        "Ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlon, zoomLevel))
                    googleMap.addMarker(
                        MarkerOptions()
                        .position(latlon)
                        .title("Nuevo Park")
                        .snippet("Abierto ahora")
                    )
                    googleMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context))
                }
            }
        }
    )
}

@Composable
fun RememberMapLifecyCle(map: MapView): LifecycleObserver {
    return remember{
        LifecycleEventObserver{source, event->
            when(event){
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}












