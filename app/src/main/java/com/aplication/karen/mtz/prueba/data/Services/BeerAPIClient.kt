package com.aplication.karen.mtz.prueba.data.Services

import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import retrofit2.Response
import retrofit2.http.GET

interface BeerAPIClient {
    @GET("/v2/beers")
    suspend fun getAllBeers(): Response<List<BeerModel>>
}