package com.aangles.cmestas.myquispeyn.screens.historial.edit

sealed class Edit2Event {
    data class EnteredName(val value: String): Edit2Event()
    data class EnteredAddress(val value: String): Edit2Event()
    data class EnteredLat(val value: String): Edit2Event()
    data class EnteredLon(val value: String): Edit2Event()
    data class EnteredDateC(val value: String): Edit2Event()
    data class EnteredDateO(val value: String): Edit2Event()
    object InsertCarPark: Edit2Event()
}
