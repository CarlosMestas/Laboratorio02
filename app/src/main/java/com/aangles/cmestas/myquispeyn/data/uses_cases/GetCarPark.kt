package com.aangles.cmestas.myquispeyn.data.uses_cases

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import com.aangles.cmestas.myquispeyn.data.repository.CarParkDBRepository
import javax.inject.Inject

class GetCarPark @Inject constructor(
    private val repository: CarParkDBRepository
) {
    suspend operator fun invoke(id: Int): CarParkDB? {
        return repository.getCarParkDBById(id)
    }
}