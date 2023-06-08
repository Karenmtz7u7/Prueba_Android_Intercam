package com.aplication.karen.mtz.prueba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aplication.karen.mtz.prueba.data.database.Entities.UserEntity

@Dao
abstract class UserDAO {

    @Query("SELECT * FROM user_table WHERE username = :username")
    abstract fun getUser(username: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(userEntity: UserEntity)

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    abstract suspend fun validateUser(username: String, password : String): List<UserEntity>

    @Query("DELETE FROM user_table")
    abstract suspend fun deleteAll()

    @Update
    abstract suspend fun update(userEntity: UserEntity)


}