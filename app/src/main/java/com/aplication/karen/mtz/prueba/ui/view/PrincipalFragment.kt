package com.aplication.karen.mtz.prueba.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.databinding.FragmentPrincipalBinding
import com.aplication.karen.mtz.prueba.ui.ViewModel.PrincipalViewModel
import com.aplication.karen.mtz.prueba.ui.adapter.BeerAdapter

class PrincipalFragment : Fragment(){
    private lateinit var binding: FragmentPrincipalBinding
    lateinit var beerAdapter: BeerAdapter
    val principalViewModel: PrincipalViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        binding = FragmentPrincipalBinding.inflate(layoutInflater)
        back()
        initRecycler()
        observeBeerModel()
        principalViewModel.onCreate()
        return binding.root
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.beerListRecyclerView.layoutManager = layoutManager
        beerAdapter = BeerAdapter(emptyList(), this, requireContext())
        binding.beerListRecyclerView.adapter = beerAdapter
        checkScroll()
    }

    private fun checkScroll() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.beerListRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val endReached = lastVisibleItem + 1 >= totalItemCount
                if (endReached) {
                    principalViewModel.loadBeers()
                }
            }
        })
    }

    private fun observeBeerModel() {
        principalViewModel.beerModel.observe(viewLifecycleOwner) { it ->
            beerAdapter.updateData(it)
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