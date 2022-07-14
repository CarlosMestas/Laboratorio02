package com.aangles.cmestas.myquispeyn.data.uses_cases

import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import com.aangles.cmestas.myquispeyn.data.repository.CarParkDBRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCarParks @Inject constructor(
    private val repository: CarParkDBRepository
) {
    operator fun invoke(): Flow<List<CarParkDB>> {
        return repository.getCarParksDB()
    }
}