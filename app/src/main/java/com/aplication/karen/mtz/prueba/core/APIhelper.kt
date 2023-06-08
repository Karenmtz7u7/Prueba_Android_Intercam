package com.aplication.karen.mtz.prueba.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIhelper {
    fun getBeer(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.punkapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}