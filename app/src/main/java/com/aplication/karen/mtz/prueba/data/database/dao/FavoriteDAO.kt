package com.aplication.karen.mtz.prueba.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aplication.karen.mtz.prueba.data.database.Entities.FavoriteEntity

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorite_table WHERE id == :idBeer")
    fun getFavorite(idBeer: Int): FavoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(beer: FavoriteEntity)

    @Delete
    fun deleteFavorite(user: FavoriteEntity)

    @Update
    fun rateBeer(beer : FavoriteEntity)

    @Query("SELECT * FROM favorite_table")
    fun getAllFavorites(): List<FavoriteEntity>

}