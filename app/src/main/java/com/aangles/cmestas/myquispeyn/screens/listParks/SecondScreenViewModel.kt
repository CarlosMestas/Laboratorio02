package com.aangles.cmestas.myquispeyn.screens.listParks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aangles.cmestas.myquispeyn.clases.CarPark
import com.aangles.cmestas.myquispeyn.paging.CarParkPagingSource
//import com.aangles.cmestas.myquispeyn.paging.UseCases
import com.aangles.cmestas.myquispeyn.repositories.CarParkRepository
import com.aangles.cmestas.myquispeyn.repositories.Result
import com.aangles.cmestas.myquispeyn.screens.listParks.SecondScreenState
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject
/*
class SecondScreenViewModel
constructor(
    private val carParkPagingSource: CarParkPagingSource
   // useCases: UseCases
): ViewModel(){

    fun getCarParks(): Flow<PagingData<CarPark>> {
        return Pager(PagingConfig(50)) {
            carParkPagingSource
        }.flow
    }

}
*/


@HiltViewModel
class SecondScreenViewModel
@Inject
constructor(
    private val carParkRepository: CarParkRepository,
    savedStateHandle: SavedStateHandle,
    private val query: Query
   // useCases: UseCases
): ViewModel(){
    private val _state: MutableState<SecondScreenState> = mutableStateOf(SecondScreenState())
    val state: State<SecondScreenState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    var regionId2 = ""

    init{
        getParkCarList()
    }


    fun getParkCarList(){
        carParkRepository.getParkCarList().onEach { result->
            when(result){
                is Result.Error -> {
                    _state.value = SecondScreenState(error = result.message ?: "Error inesperado")
                }
                is Result.Loading -> {
                    _state.value = SecondScreenState(isLoading = true)
                }
                is Result.Success -> {
                    _state.value = SecondScreenState(carparks = result.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllCarParks(){
        val carParks: Flow<PagingData<CarPark>> = Pager(PagingConfig(pageSize = 5)) {
            CarParkPagingSource(query)
        }.flow
    }

    fun getAll(): Flow<PagingData<CarPark>>{
        return carParks
    }

    val carParks: Flow<PagingData<CarPark>> = Pager(PagingConfig(pageSize = 5)) {
        CarParkPagingSource(query)
    }.flow


}
