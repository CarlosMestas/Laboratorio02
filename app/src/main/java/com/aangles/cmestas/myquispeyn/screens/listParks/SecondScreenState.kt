package com.aangles.cmestas.myquispeyn.screens.listParks

import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.aangles.cmestas.myquispeyn.clases.MyItem

data class SecondScreenState (
    val isLoading: Boolean = false,
    val carparks: List<CarPark> = emptyList(),
    val error: String = ""
)