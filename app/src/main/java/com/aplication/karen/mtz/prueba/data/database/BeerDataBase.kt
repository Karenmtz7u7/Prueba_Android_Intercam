package com.aplication.karen.mtz.prueba.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aplication.karen.mtz.prueba.data.database.Entities.FavoriteEntity
import com.aplication.karen.mtz.prueba.data.database.Entities.UserEntity
import com.aplication.karen.mtz.prueba.data.database.dao.FavoriteDAO
import com.aplication.karen.mtz.prueba.data.database.dao.UserDAO


@Database(entities = [FavoriteEntity::class, UserEntity::class], version = 1)
    abstract class BeerDataBase : RoomDatabase() {

        abstract fun userDao(): UserDAO
        abstract fun favoriteDao(): FavoriteDAO

        companion object {
            private var INSTANCE: BeerDataBase? = null

            fun getInstance(context: Context): BeerDataBase? {
                if (INSTANCE == null) {
                    synchronized(BeerDataBase::class) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BeerDataBase::class.java, "users.db"
                        ).allowMainThreadQueries()
                            .build()
                    }
                }
                return INSTANCE
            }


        }

    }
