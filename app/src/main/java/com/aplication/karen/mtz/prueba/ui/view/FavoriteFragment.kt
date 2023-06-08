package com.aplication.karen.mtz.prueba.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.databinding.FragmentFavoriteBinding
import com.aplication.karen.mtz.prueba.databinding.FragmentPrincipalBinding
import com.aplication.karen.mtz.prueba.ui.ViewModel.FavoriteViewModel
import com.aplication.karen.mtz.prueba.ui.ViewModel.PrincipalViewModel
import com.aplication.karen.mtz.prueba.ui.adapter.BeerAdapter
import com.aplication.karen.mtz.prueba.ui.adapter.FavoriteAdapter


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteAdapter: FavoriteAdapter
    val favoriteViewModel : FavoriteViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        back()
        initRecycler()
        observeBeerModel()

        return binding.root
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.FavListRecyclerView.layoutManager = layoutManager
        favoriteAdapter = FavoriteAdapter(emptyList(), this, requireContext())
        binding.FavListRecyclerView.adapter = favoriteAdapter

    }

    private fun observeBeerModel() {
        favoriteViewModel.getAllFavourites().observe(viewLifecycleOwner) { favorites ->
            favoriteAdapter.favorites = favorites
            favoriteAdapter.notifyDataSetChanged()
        }
    }

    fun back(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            exitDialog()
        }
    }

    private fun exitDialog() {
        val view = View.inflate(context, R.layout.dialog_view, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(view)
        val dialog = builder.create()
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        view.findViewById<Button>(R.id.botonCancel).setOnClickListener {
            dialog.dismiss()
        }
        view.findViewById<Button>(R.id.botonAcept).setOnClickListener{
            dialog.dismiss()
            requireActivity().moveTaskToBack(true);
            requireActivity().finish()
        }
    }

}