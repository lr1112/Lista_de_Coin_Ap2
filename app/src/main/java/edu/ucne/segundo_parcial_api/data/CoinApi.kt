package edu.ucne.segundo_parcial_api.data

import edu.ucne.segundo_parcial_api.data.dto.CoinDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoinApi {
    @GET("/Coinst")
    suspend fun getCoins(): List<CoinDto>

    @POST("/Coinst")
    suspend fun PostInser(@Body coinDto: CoinDto): CoinDto
}