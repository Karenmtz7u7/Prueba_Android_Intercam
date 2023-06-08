package com.aplication.karen.mtz.prueba.data.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val idbeer: Int = 0,
    @ColumnInfo(name = "name") val beername: String,
    @ColumnInfo(name = "rate") val ratebeer: Float = 0.0f,
    )