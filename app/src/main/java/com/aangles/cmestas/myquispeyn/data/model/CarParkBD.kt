package com.aangles.cmestas.myquispeyn.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CarParkDB(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val address: String,
    val lat: String,
    val lon: String,
    val dateC: String,
    val dateO: String
)