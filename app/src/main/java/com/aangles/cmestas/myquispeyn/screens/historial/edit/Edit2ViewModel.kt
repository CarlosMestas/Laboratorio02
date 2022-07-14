package com.aangles.cmestas.myquispeyn.screens.historial.edit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import com.aangles.cmestas.myquispeyn.data.uses_cases.GetCarPark
import com.aangles.cmestas.myquispeyn.data.uses_cases.InsertCarPark
import com.aangles.cmestas.myquispeyn.screens.historial.edit.components.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Edit2ViewModel @Inject constructor(
    private val getCarPark: GetCarPark,
    private val insertCarPark: InsertCarPark,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _carParkName = mutableStateOf(TextFieldState())
    val carParkName: State<TextFieldState> = _carParkName

    private val _carParkAddress = mutableStateOf(TextFieldState())
    val carParkAddress: State<TextFieldState> = _carParkAddress

    private val _carParkLat = mutableStateOf(TextFieldState())
    val carParkLat: State<TextFieldState> = _carParkLat

    private val _carParkLon = mutableStateOf(TextFieldState())
    val carParkLon: State<TextFieldState> = _carParkLon

    private val _carParkDateC = mutableStateOf(TextFieldState())
    val carParkDateC: State<TextFieldState> = _carParkDateC

    private val _carParkDateO = mutableStateOf(TextFieldState())
    val carParkDateO: State<TextFieldState> = _carParkDateO

    private val _eventFlow = MutableSharedFlow<Ui2Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentCarParkId: Int? = null

    init {
        savedStateHandle.get<Int>("carParkId")?.let { carParkId ->
            if (carParkId != -1) {
                viewModelScope.launch {
                    getCarPark(carParkId)?.also { carPark ->
                        currentCarParkId = carPark.id
                        _carParkName.value = carParkName.value.copy(
                            text = carPark.name
                        )
                        _carParkAddress.value = carParkAddress.value.copy(
                            text = carPark.address
                        )
                        _carParkLat.value = carParkLat.value.copy(
                            text = carPark.lat
                        )
                        _carParkLon.value = carParkLon.value.copy(
                            text = carPark.lon
                        )
                        _carParkDateC.value = carParkDateC.value.copy(
                            text = carPark.dateC
                        )
                        _carParkDateO.value = carParkDateO.value.copy(
                            text = carPark.dateO
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: Edit2Event) {
        when (event) {
            is Edit2Event.EnteredName -> {
                _carParkName.value = carParkName.value.copy(
                    text = event.value
                )
            }
            is Edit2Event.EnteredAddress -> {
                _carParkAddress.value = carParkAddress.value.copy(
                    text = event.value
                )
            }
            is Edit2Event.EnteredLat -> {
                _carParkLat.value = carParkLat.value.copy(
                    text = event.value
                )
            }
            is Edit2Event.EnteredLon -> {
                _carParkLon.value = carParkLon.value.copy(
                    text = event.value
                )
            }

            is Edit2Event.EnteredDateC -> {
                _carParkDateC.value = carParkDateC.value.copy(
                    text = event.value
                )
            }

            is Edit2Event.EnteredDateO -> {
                _carParkDateO.value = carParkDateO.value.copy(
                    text = event.value
                )
            }
            Edit2Event.InsertCarPark -> {
                viewModelScope.launch {
                    insertCarPark(
                        CarParkDB(
                            name = carParkName.value.text,
                            address = carParkAddress.value.text,
                            lat = carParkLat.value.text,
                            lon = carParkLon.value.text,
                            dateC = carParkDateC.value.text,
                            dateO = carParkDateO.value.text,
                            id = currentCarParkId
                        )
                    )
                    _eventFlow.emit(Ui2Event.SaveCarPark)
                }
            }
        }
    }

    sealed class Ui2Event {
        object SaveCarPark: Ui2Event()
    }
}