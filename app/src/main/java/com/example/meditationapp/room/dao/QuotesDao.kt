package com.example.meditationapp.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meditationapp.room.entities.FeelingEntity
import com.example.meditationapp.room.entities.QuoteEntity

@Dao
interface QuotesDao {
    @Query("SELECT * FROM quotes")
    fun getAll(): List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<QuoteEntity>)
}