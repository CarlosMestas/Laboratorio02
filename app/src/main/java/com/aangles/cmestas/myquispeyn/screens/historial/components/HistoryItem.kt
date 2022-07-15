package com.aangles.cmestas.myquispeyn.screens.historial.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import com.aangles.cmestas.myquispeyn.ui.theme.Laboratorio02Theme

@Composable
fun HistoryItem(
    modifier: Modifier = Modifier,
    carPark: CarParkDB,
    onDeleteCarPark: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = carPark.name,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = carPark.address,
                    style = MaterialTheme.typography.caption.copy(color = Color.DarkGray)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Horario: ${carPark.dateO} - ${carPark.dateC}",
                    style = MaterialTheme.typography.caption.copy(color = Color.DarkGray)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Ubicación: ${carPark.lat} - ${carPark.lon}",
                    style = MaterialTheme.typography.caption.copy(color = Color.DarkGray)
                )

            }
            Row {
                IconButton(onClick = onDeleteCarPark) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }
    }
}