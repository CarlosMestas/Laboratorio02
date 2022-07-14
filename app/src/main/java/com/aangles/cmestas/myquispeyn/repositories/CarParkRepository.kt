package com.aangles.cmestas.myquispeyn.repositories

import androidx.paging.PagingConfig
import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.aangles.cmestas.myquispeyn.clases.MyItem
import com.aangles.cmestas.myquispeyn.paging.*
import com.aangles.cmestas.myquispeyn.screens.regionIdFinal
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarParkRepository
@Inject
constructor(
    private val carParkList: FirebaseFirestore
){

    fun getParkCarList(): Flow<Result<List<CarPark>>> = flow{
        try{
            emit(Result.Loading<List<CarPark>>())
            val carParkList = carParkList
                .collection("carparks")
                .whereEqualTo("regionId", regionIdFinal)
                .get().await().map{document->
                document.toObject(CarPark::class.java)
            }

            emit(Result.Success<List<CarPark>>(data = carParkList))
        }catch (e: Exception){
            emit(Result.Error<List<CarPark>>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }


}