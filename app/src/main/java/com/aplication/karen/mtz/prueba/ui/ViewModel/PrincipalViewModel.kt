package com.aplication.karen.mtz.prueba.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.domain.GetBeerUseCase
import kotlinx.coroutines.launch

class PrincipalViewModel : ViewModel() {
    val _beerModel = MutableLiveData<List<BeerModel>>()
    val beerModel: LiveData<List<BeerModel>> = _beerModel
    private var page = 1
    private val perPage = 50
    private var isLoading = false

    var getBeerUseCase = GetBeerUseCase()

    fun onCreate(){ loadBeers() }

    fun loadBeers(){
        if(!isLoading){
            page++
            viewBeers()
        }
    }

    fun viewBeers(){
        viewModelScope.launch {
            try{
                isLoading = true
                val response = getBeerUseCase(page, perPage)
                if(!response.isNullOrEmpty()){
                    val currentList = _beerModel.value.orEmpty().toMutableList()
                    currentList.addAll(response)
                    _beerModel.postValue(currentList)
                }else{
                    print("No se pudo tener la lista")
                }
            }catch (e:Exception){
                print("Message error: $e")
            }finally {
                isLoading = false
            }

        }

    }
}
