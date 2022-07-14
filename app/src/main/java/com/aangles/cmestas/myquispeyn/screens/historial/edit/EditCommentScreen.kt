package com.aangles.cmestas.myquispeyn.screens.historial.edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aangles.cmestas.myquispeyn.R
import com.aangles.cmestas.myquispeyn.screens.historial.edit.components.UserInputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditCommentScreen(
    navController: NavController,
    viewModel: Edit2ViewModel = hiltViewModel()
) {
    val nameState = viewModel.carParkName.value
    val addressState = viewModel.carParkAddress.value
    val latState = viewModel.carParkLat.value
    val lonState = viewModel.carParkLon.value
    val dateCState = viewModel.carParkDateC.value
    val dateOState = viewModel.carParkDateO.value

    val id = viewModel.currentCarParkId

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is Edit2ViewModel.Ui2Event.SaveCarPark -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            if(id!=null) {
                EditTopBar(
                    topAppBarText = stringResource(id = R.string.modifier_carPark)
                )
            }
            else
                EditTopBar(
                    topAppBarText = stringResource(id = R.string.add_carPark)
                )
        },
        content = {
            EditContent(
                name = nameState.text,
                address = addressState.text,
                lat = latState.text,
                lon = lonState.text,
                dateC = dateCState.text,
                dateO = dateOState.text,
                onEvent = { viewModel.onEvent(it) }
            )
        },
        bottomBar = {
            if (id != null) {
                EditBottomBar(
                    id,
                    onInsertCarPark = { viewModel.onEvent(Edit2Event.InsertCarPark) }
                )
            }
            else{
                EditBottomBar(
                    -1,
                    onInsertCarPark = { viewModel.onEvent(Edit2Event.InsertCarPark) }
                )
            }

        }
    )
}

@Composable
fun EditTopBar(topAppBarText: String) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun EditContent(
    name: String,
    address: String,
    lat: String,
    lon: String,
    dateC: String,
    dateO: String,
    onEvent: (Edit2Event) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(44.dp))
        UserInputText(
            text = name,
            hint = stringResource(id = R.string.name),
            onTextChange = { onEvent(Edit2Event.EnteredName(it)) }
        )
        UserInputText(
            text = address,
            hint = stringResource(id = R.string.address),
            onTextChange = { onEvent(Edit2Event.EnteredAddress(it)) }
        )
        UserInputText(
            text = lat,
            hint = stringResource(id = R.string.latitude),
            onTextChange = { onEvent(Edit2Event.EnteredLat(it)) }
        )
        UserInputText(
            text = lon,
            hint = stringResource(id = R.string.longitude),
            onTextChange = { onEvent(Edit2Event.EnteredLon(it)) }
        )
        UserInputText(
            text = dateC,
            hint = stringResource(id = R.string.hourC),
            onTextChange = { onEvent(Edit2Event.EnteredDateC(it)) }
        )
        UserInputText(
            text = dateO,
            hint = stringResource(id = R.string.hourB),
            onTextChange = { onEvent(Edit2Event.EnteredDateO(it)) }
        )
    }
}

@Composable
fun EditBottomBar(
    text: Int,
    modifier: Modifier = Modifier,
    onInsertCarPark: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp, 75.dp),
        onClick = { onInsertCarPark() }
    ) {
        if(text == -1)
            Text(text = stringResource(id = R.string.add_carPark))
        else
            Text(text = stringResource(id = R.string.modifier_carPark))
    }
}
