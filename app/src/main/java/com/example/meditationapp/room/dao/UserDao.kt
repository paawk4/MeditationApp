package com.example.meditationapp.room.dao

import androidx.room.*
import com.example.meditationapp.room.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)
    @Delete
    fun deleteUser(user: UserEntity)
}