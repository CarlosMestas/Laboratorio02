package com.aangles.cmestas.myquispeyn.data.uses_cases

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import com.aangles.cmestas.myquispeyn.data.repository.CarParkDBRepository
import javax.inject.Inject

class DeleteCarPark @Inject constructor(
    private val repository: CarParkDBRepository
) {
    suspend operator fun invoke(carpark: CarParkDB) {
        repository.deleteCarParkDB(carpark)
    }
}