package com.aplication.karen.mtz.prueba.ui.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aplication.karen.mtz.prueba.domain.Repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> get() = _loginResult


    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginRepository.logIn(email, password).addOnCompleteListener {
                _loginResult.value = it.isSuccessful
            }.addOnFailureListener{
                _loginResult.value = false
            }

        }
    }
}