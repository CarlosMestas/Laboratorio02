package com.aangles.cmestas.myquispeyn.data.uses_cases

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import com.aangles.cmestas.myquispeyn.data.repository.CarParkDBRepository
import javax.inject.Inject

class InsertCarPark @Inject constructor(
    private val repository: CarParkDBRepository
) {
    suspend operator fun invoke(carPark: CarParkDB) {
        repository.insertCarParkDB(carPark)
    }
}