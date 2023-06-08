package com.aplication.karen.mtz.prueba.data.Services

import com.aplication.karen.mtz.prueba.core.APIhelper
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BeerService {
    private val retrofit = APIhelper.getBeer()

    suspend fun getBeers(): List<BeerModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(BeerAPIClient::class.java).getAllBeers()
            response.body() ?: emptyList()
        }
    }
}