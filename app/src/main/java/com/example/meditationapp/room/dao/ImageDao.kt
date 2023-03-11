package com.example.meditationapp.room.dao

import androidx.room.*
import com.example.meditationapp.room.entities.FeelingEntity
import com.example.meditationapp.room.entities.ImageEntity

@Dao
interface ImageDao {
    @Query("SELECT * FROM images")
    fun getAll(): List<ImageEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImageEntity)
    @Delete
    fun deleteImage(image: ImageEntity)
}