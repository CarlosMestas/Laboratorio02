package com.aangles.cmestas.myquispeyn.data.local.dao

import androidx.room.*
import com.aangles.cmestas.myquispeyn.data.model.CarParkDB
import kotlinx.coroutines.flow.Flow

@Dao
interface CarParkDBDao {
    @Query("SELECT * FROM CarParkDB")
    fun getCarParksDB(): Flow<List<CarParkDB>>

    @Query("SELECT * FROM CarParkDB WHERE id = :id")
    suspend fun getCarParkDBById(id: Int): CarParkDB?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCarParkDB(carPark: CarParkDB)

    @Delete
    suspend fun deleteCarParkDB(carPark: CarParkDB)
/*
    @Query("SELECT EXISTS(SELECT 1 FROM CarParkDB WHERE id = :id)")
    fun exists(id: Int): Int
*/
}