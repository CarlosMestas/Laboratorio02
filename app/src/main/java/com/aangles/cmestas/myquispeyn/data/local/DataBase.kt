package com.aangles.cmestas.myquispeyn.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aangles.cmestas.myquispeyn.data.local.dao.CarParkDBDao
import com.aangles.cmestas.myquispeyn.data.model.CarParkDB

@Database(
    entities = [CarParkDB::class],
    version = 3,
    exportSchema = false
)

abstract class DataBaseDB: RoomDatabase() {
    abstract fun carParkDao(): CarParkDBDao

}