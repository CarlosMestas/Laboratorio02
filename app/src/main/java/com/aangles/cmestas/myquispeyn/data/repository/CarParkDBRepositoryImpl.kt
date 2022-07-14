package com.aangles.cmestas.myquispeyn.data.repository

import com.aangles.cmestas.myquispeyn.data.local.dao.CarParkDBDao
import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarParkDBRepositoryImpl @Inject constructor(
    private val dao: CarParkDBDao
): CarParkDBRepository {
    override fun getCarParksDB(): Flow<List<CarParkDB>> {
        return dao.getCarParksDB()
    }

    override suspend fun getCarParkDBById(id: Int): CarParkDB? {
        return dao.getCarParkDBById(id)
    }

    override suspend fun insertCarParkDB(carPark: CarParkDB) {
        dao.insertCarParkDB(carPark)
    }

    override suspend fun deleteCarParkDB(carPark: CarParkDB) {
        dao.deleteCarParkDB(carPark)
    }


}