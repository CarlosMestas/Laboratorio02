package com.aangles.cmestas.myquispeyn.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class CarParkPagingSource (
    private val queryCarkParkByRegion: Query
): PagingSource<QuerySnapshot, CarPark>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, CarPark>): QuerySnapshot? ?= null

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, CarPark> {
        return try{
            val currentPage = params.key ?: queryCarkParkByRegion.get().await()
            val lastVisibleCarPark = currentPage.documents[currentPage.size() - 1]
            val nextPage = queryCarkParkByRegion.startAfter(lastVisibleCarPark).get().await()
            LoadResult.Page(
                data = currentPage.toObjects(CarPark::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}

/*
class CarParkPagingSource (
    private val queryCarkParkByRegion: Query
): PagingSource<QuerySnapshot, CarPark>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, CarPark>): QuerySnapshot? ?= null

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, CarPark> {
        return try{
            val currentPage = params.key ?: queryCarkParkByRegion.get().await()
            val lastVisibleCarPark = currentPage.documents[currentPage.size() - 1]
            val nextPage = queryCarkParkByRegion.startAfter(lastVisibleCarPark).get().await()
            LoadResult.Page(
                data = currentPage.toObjects(CarPark::class.java),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
*/