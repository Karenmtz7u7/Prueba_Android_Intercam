package com.aplication.karen.mtz.prueba.data.Repository

import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.data.Provider.BeerProvider
import com.aplication.karen.mtz.prueba.data.Services.BeerService

    class BeerRepository {
        private val api = BeerService()

        suspend fun getAllBeers():List<BeerModel>{
            val response = api.getBeers()
            BeerProvider.beers = response
            return response
        }
    }
