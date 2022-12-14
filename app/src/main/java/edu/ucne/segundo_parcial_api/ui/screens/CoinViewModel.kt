package edu.ucne.segundo_parcial_api.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.segundo_parcial_api.data.CoinRepository
import edu.ucne.segundo_parcial_api.data.dto.CoinDto
import edu.ucne.segundo_parcial_api.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
): ViewModel() {
    var descripcion by mutableStateOf("")
    var imageUrl by mutableStateOf("")
    var valor by mutableStateOf("")

    private var _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        recargarLista()
    }

    fun recargarLista() {
        coinRepository.getCoin().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
    fun Guardar(){
        viewModelScope.launch {
            coinRepository.Inser(
                CoinDto(
                    descripcion = descripcion,
                    imageUrl = imageUrl,
                    valor = valor
                )
            )
        }
    }
}