package com.aplication.karen.mtz.prueba.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {
    val beerDetails = MutableLiveData<BeerModel>()

    fun setbeerDetails(beer: BeerModel) {
        viewModelScope.launch {
            delay(100)
            beerDetails.value = beer
        }
    }

}