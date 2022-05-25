package com.aangles.cmestas.myquispeyn.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aangles.cmestas.myquispeyn.repositories.CarParkRepository
import com.aangles.cmestas.myquispeyn.repositories.RegionRepository
import com.aangles.cmestas.myquispeyn.repositories.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel
@Inject
constructor(
    private val carParkRepository: CarParkRepository,
    savedStateHandle: SavedStateHandle,
): ViewModel(){
    private val _state: MutableState<SecondScreenState> = mutableStateOf(SecondScreenState())
    val state: State<SecondScreenState> = _state

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

//    var regionId: String = ""


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

}