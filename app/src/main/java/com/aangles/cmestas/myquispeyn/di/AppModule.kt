package com.aangles.cmestas.myquispeyn.di

import android.app.Application
import androidx.room.Room
import com.aangles.cmestas.myquispeyn.paging.*
import com.aangles.cmestas.myquispeyn.utils.DATABASE_NAME
import com.google.firebase.firestore.FirebaseFirestore
import com.aangles.cmestas.myquispeyn.data.local.DataBaseDB
import com.aangles.cmestas.myquispeyn.data.repository.CarParkDBRepository
import com.aangles.cmestas.myquispeyn.data.repository.CarParkDBRepositoryImpl
import com.google.firebase.firestore.Query
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideFireStoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideQueryCarParksByName() = FirebaseFirestore.getInstance()
        .collection("CarPark")
        .orderBy("name", Query.Direction.ASCENDING)
        .limit(5.toLong())

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        DataBaseDB::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRepository2(db: DataBaseDB): CarParkDBRepository {
        return CarParkDBRepositoryImpl(db.carParkDao())
    }
}