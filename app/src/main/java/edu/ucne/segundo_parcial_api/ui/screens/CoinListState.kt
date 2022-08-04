package edu.ucne.segundo_parcial_api.ui.screens

import edu.ucne.segundo_parcial_api.data.dto.CoinDto

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinDto> = emptyList(),
    val error: String = " "
)
