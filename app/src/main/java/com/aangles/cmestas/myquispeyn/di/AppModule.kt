package com.aangles.cmestas.myquispeyn.di

import androidx.paging.PagingConfig
import com.aangles.cmestas.myquispeyn.paging.*
import com.aangles.cmestas.myquispeyn.screens.regionIdFinal
import com.google.firebase.firestore.FirebaseFirestore
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

}