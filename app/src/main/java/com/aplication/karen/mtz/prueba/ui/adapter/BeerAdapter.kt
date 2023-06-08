package com.aplication.karen.mtz.prueba.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.aplication.karen.mtz.prueba.R
import com.aplication.karen.mtz.prueba.data.Model.BeerModel
import com.aplication.karen.mtz.prueba.data.database.BeerDataBase
import com.aplication.karen.mtz.prueba.data.database.Entities.FavoriteEntity
import com.aplication.karen.mtz.prueba.ui.view.DetailsFragment
import com.squareup.picasso.Picasso


class BeerAdapter (private var beerList: List<BeerModel>,
                       val fragment: Fragment,
    val context: Context) :
        RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.beer_item, parent, false)
            return BeerViewHolder(view)
        }
        // Se establece la información en los textView
        override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
            val item = beerList[position]
            holder.bind(item, fragment, context)
        }

        override fun getItemCount(): Int {
            return beerList.size
        }
        inner class BeerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val beerName = view.findViewById<TextView>(R.id.beerName)
            val beerTagLine = view.findViewById<TextView>(R.id.beerTagline)
            val beerImage = view.findViewById<ImageView>(R.id.beerImage)
            val beerFav = view.findViewById<ImageView>(R.id.beerFav)

            fun bind(beer : BeerModel, fragment: Fragment, context: Context){
                Picasso.get()
                    .load(beer.image_url)
                    .into(beerImage)
                beerName.text = beer.name
                beerTagLine.text = beer.tagline

                itemView.setOnClickListener {
                    val dialogFragment: DetailsFragment = DetailsFragment.newInstance(beer)
                    val activity = it.context as AppCompatActivity
                    dialogFragment.show(activity.supportFragmentManager, null)
                }

                val database : BeerDataBase? = BeerDataBase.getInstance(context)
                val isFavorite = beer.isfavorite
                if (isFavorite) { beerFav.setImageResource(R.drawable.ic_favorite)}
                else { beerFav.setImageResource(R.drawable.ic_favorite__24) }
                beerFav.setOnClickListener {
                    beer.isfavorite = !isFavorite

                    if (beer.isfavorite) {
                        beerFav.setImageResource(R.drawable.ic_favorite)
                        val favoriteEntity = FavoriteEntity(beer.id, beer.name)
                        database?.favoriteDao()?.insertFavorite(favoriteEntity)
                    } else {
                        beerFav.setImageResource(R.drawable.ic_favorite__24)
                        val favoriteEntity = FavoriteEntity(beer.id, beer.name)
                        database?.favoriteDao()?.deleteFavorite(favoriteEntity)
                    }
                }

            }
        }

        fun updateData(newBeerList: List<BeerModel>) {
            beerList = newBeerList
            notifyDataSetChanged()
        }
    }
