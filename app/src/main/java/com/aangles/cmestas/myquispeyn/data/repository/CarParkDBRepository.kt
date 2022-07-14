package com.aangles.cmestas.myquispeyn.data.repository

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import kotlinx.coroutines.flow.Flow

interface CarParkDBRepository {
    fun getCarParksDB(): Flow<List<CarParkDB>>

    suspend fun getCarParkDBById(id: Int): CarParkDB?

    suspend fun insertCarParkDB(carPark: CarParkDB)

    suspend fun deleteCarParkDB(carPark: CarParkDB)

}