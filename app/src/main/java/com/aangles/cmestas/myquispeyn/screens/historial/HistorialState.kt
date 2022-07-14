package com.aangles.cmestas.myquispeyn.screens.historial

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB

data class HistorialState(
    val carParks: List<CarParkDB> = emptyList()
)
