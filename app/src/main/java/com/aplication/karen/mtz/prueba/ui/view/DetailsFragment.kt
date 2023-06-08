package com.aplication.karen.mtz.prueba.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.databinding.FragmentDetailsBinding
import com.aplication.karen.mtz.prueba.ui.ViewModel.DetailsViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class DetailsFragment(): DialogFragment(){

    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel : DetailsViewModel by viewModels()


    companion object{
        const val TAG = "DetailsFragment"
        fun newInstance(beer: BeerModel): DetailsFragment {
            val args = Bundle()
            args.putString("beer", Gson().toJson(beer))
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        getDetails()

        return binding.root
    }

    fun getDetails(){
        val beerJson = arguments?.getString("beer")
        val beer = Gson().fromJson(beerJson, BeerModel::class.java)
        detailsViewModel.setbeerDetails(beer)
        BeerDetails()
    }


    private fun BeerDetails() {
        detailsViewModel.beerDetails.observe(viewLifecycleOwner) { beer ->
            binding.beerNametxt.text = beer.name
            Picasso.get()
                .load(beer.image_url)
                .into(binding.beerImg)
            binding.beerYeasttxt.text = beer.ingredients.yeast
            binding.beerVolumetxt.text = beer.volume.unit
            binding.beerAbvtxt.text = beer.abv.toString()
            binding.beerIbutxt.text = beer.ibu.toString()
            binding.beerOgtxt.text = beer.target_og.toString()
            binding.beerFgtxt.text = beer.target_fg.toString()
            binding.beerDescriptiontxt.text = beer.description
            binding.beerDetBrewed.text = beer.first_brewed
            binding.beerTipstxt.text = beer.brewers_tips
            binding.beerFoodtxt.text = beer.food_pairing.toString()
            binding.beerHopstxt.text = beer.ingredients.hops[0].name
            binding.beerMalttxt.text = beer.ingredients.malt[0].name
        }
    }

}