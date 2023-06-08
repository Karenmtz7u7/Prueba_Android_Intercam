package com.aplication.karen.mtz.prueba.domain

import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.data.Repository.BeerRepository

class GetBeerUseCase {
    private val repository = BeerRepository()
    suspend operator fun invoke(page: Int, perPage: Int): List<BeerModel> = repository.getAllBeers()
}