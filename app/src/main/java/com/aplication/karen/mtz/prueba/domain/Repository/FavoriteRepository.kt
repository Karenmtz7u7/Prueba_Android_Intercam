package com.aplication.karen.mtz.prueba.domain.Repository

import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.data.database.BeerDataBase
import com.aplication.karen.mtz.prueba.data.database.Entities.FavoriteEntity
import com.google.gson.Gson

class FavoriteRepository(private  val database: BeerDataBase){

    fun saveFavourite(beer: BeerModel) {
        database.favoriteDao().insertFavorite(FavoriteEntity(beer.id, Gson().toJson(beer)))
    }

    fun deleteFavourite(beer: BeerModel) {
        database.favoriteDao().deleteFavorite(FavoriteEntity(beer.id, Gson().toJson(beer)))
    }

    fun isBeerFavourite(idBeer: Int): Boolean {
        val result = database.favoriteDao().getFavorite(idBeer)
        return result != null
    }

    fun getAllFavourites(): List<FavoriteEntity> {
        return database.favoriteDao().getAllFavorites()
    }

    fun rateBeer(idBeer: Int, beername: String, rate: Float,) {
        val beer = FavoriteEntity(idBeer, beername, rate)
        database.favoriteDao().rateBeer(beer)
    }
}