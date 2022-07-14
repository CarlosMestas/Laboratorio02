package com.aangles.cmestas.myquispeyn.screens.historial

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB

sealed class CarParkEvent {
    data class DeleteCarPark(val carPark: CarParkDB): CarParkEvent()
}
