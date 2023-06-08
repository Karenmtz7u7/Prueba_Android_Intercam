package com.aplication.karen.mtz.prueba.domain.Repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginRepository {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    fun logIn(email:String, password: String):Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)

    }


    fun getid(): String{
        return auth.currentUser?.uid ?: ""
    }
}