package com.aplication.karen.mtz.prueba.domain.ModelDomain

import com.aplication.karen.mtz.prueba.data.Model.UserModel
import com.aplication.karen.mtz.prueba.data.database.Entities.UserEntity


data class User(
        var email: String,
        var password: String)

    fun UserModel.toDomain() = User(email,password)
    fun UserEntity.toDomain() = User(email,password)