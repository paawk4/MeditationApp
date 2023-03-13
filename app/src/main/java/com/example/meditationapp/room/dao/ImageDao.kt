package com.example.meditationapp.room.dao

import androidx.room.*
import com.example.meditationapp.room.entities.FeelingEntity
import com.example.meditationapp.room.entities.ImageEntity

@Dao
interface ImageDao {
    @Transaction
    @Query("SELECT * FROM images")
    fun getAll(): List<ImageEntity>
    @Transaction
    @Query("SELECT * FROM images WHERE id = :imageId")
    fun getImageById(imageId: Int): ImageEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImageEntity)
    @Query("DELETE FROM images WHERE id = :imageId")
    fun deleteImageById(imageId: Int)
}