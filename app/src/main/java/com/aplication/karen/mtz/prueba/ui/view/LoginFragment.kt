package com.aplication.karen.mtz.prueba.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aplication.karen.mtz.prueba.MainActivity
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.databinding.FragmentLoginBinding
import com.aplication.karen.mtz.prueba.ui.ViewModel.LoginViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.ingresarbtn.setOnClickListener { checkText() }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeLive()
        super.onViewCreated(view, savedInstanceState)
    }

    fun observeLive(){
        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer { success ->
            if (success == true) {
                goPrincipal()
            } else {
                showSnackbar("Usuario y contraseña incorrectos intente de nuevo.")
            }
        })
    }

    private fun checkText() {
        val username = binding.usertxt.text.toString()
        val password = binding.passwordtxt.text.toString()

        if(username.isEmpty() || password.isEmpty()){
            binding.usertxt.error = "Campo vacio"
            binding.passwordtxt.error = "Campo vacio"
            showSnackbar("No puedes dejar campos vacios")
        } else{
            loginViewModel.login(username, password)
        }
    }

    fun goPrincipal(){
        val mainActivity = activity as? MainActivity
        mainActivity?.principalFragment()

    }
    fun showSnackbar(message : String){
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

}