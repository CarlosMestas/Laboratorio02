package com.aangles.cmestas.myquispeyn.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot

class CarParkPagingSource (
    private val queryCarkParkByRegion: Query
): PagingSource<QuerySnapshot, CarPark>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, CarPark>): QuerySnapshot? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, CarPark> {
        TODO("Not yet implemented")
    }

}