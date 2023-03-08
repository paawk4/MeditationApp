package com.example.meditationapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meditationapp.room.entities.FeelingEntity

@Dao
interface FeelingsDao {
    @Query("SELECT * FROM feelings")
    fun getAll(): List<FeelingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<FeelingEntity>)
}