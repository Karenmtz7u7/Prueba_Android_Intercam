package com.aplication.karen.mtz.prueba.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.data.database.BeerDataBase
import com.aplication.karen.mtz.prueba.data.database.Entities.FavoriteEntity
import com.aplication.karen.mtz.prueba.ui.view.DetailsFragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class FavoriteAdapter(
    var favorites: List<FavoriteEntity>,
    val fragment: Fragment,
    val context: Context) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        val item = favorites[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    inner class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beerName = view.findViewById<TextView>(R.id.beerName)
        val beerTagLine = view.findViewById<TextView>(R.id.beerTagline)
        val beerImage = view.findViewById<ImageView>(R.id.beerImage)
        val rate = view.findViewById<RatingBar>(R.id.rate_beer)

        fun bind(favoriteEntity: FavoriteEntity) {
            var beer = Gson().fromJson(favoriteEntity.beername, BeerModel::class.java)
            Picasso.get()
                .load(beer.image_url)
                .into(beerImage)
            beerName.text = beer.name
            beerTagLine.text = beer.tagline
            rate.rating = favoriteEntity.ratebeer
            rate.onRatingBarChangeListener =
                RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->

                    val database : BeerDataBase? = BeerDataBase.getInstance(context)
                    database?.favoriteDao()?.rateBeer(FavoriteEntity(favoriteEntity.idbeer, favoriteEntity.beername, rating))
                }
        }
    }
}
