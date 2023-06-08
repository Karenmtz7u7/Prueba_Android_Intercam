package com.aplication.karen.mtz.prueba.ui.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aplication.karen.mtz.prueba.MainActivity
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.databinding.FragmentSplashBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        showGif()

        Handler().postDelayed({
            val mainActivity = activity as? MainActivity
            mainActivity?.loginFragment()
        }, 1500L)

        return binding.root

    }

    fun showGif(){

        Glide.with(this)
            .asGif()
            .load(R.drawable.beersplash)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.textViewBeer)

    }

}