package com.aplication.karen.mtz.prueba.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.data.database.BeerDataBase
import com.aplication.karen.mtz.prueba.data.database.Entities.FavoriteEntity
import com.aplication.karen.mtz.prueba.domain.Repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {


        private lateinit var favRepository: FavoriteRepository

        fun init(database: BeerDataBase) {

            favRepository = FavoriteRepository(database)
        }


        fun saveFavourite(beer: BeerModel) {
            viewModelScope.launch {
                favRepository.saveFavourite(beer)
            }
        }

        fun deleteFavourite(beer: BeerModel) {
            viewModelScope.launch {
                favRepository.deleteFavourite(beer)
            }
        }

        fun isBeerFavourite(idBeer: Int): LiveData<Boolean> {
            val isFav = MutableLiveData<Boolean>()
            viewModelScope.launch {
                val result = favRepository.isBeerFavourite(idBeer)
                isFav.value = result
            }
            return isFav
        }

        fun getAllFavourites(): LiveData<List<FavoriteEntity>> {
            val fav = MutableLiveData<List<FavoriteEntity>>()
            viewModelScope.launch {
                val result = favRepository.getAllFavourites()
                fav.value = result
            }
            return fav
        }

        fun rateBeer(idBeer: Int, beer: String, rate: Float) {
            viewModelScope.launch {
                favRepository.rateBeer(idBeer, beer, rate)
            }
        }


}