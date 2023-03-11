package com.example.meditationapp.room.entities

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.intellij.lang.annotations.JdkConstants.CursorType

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String,
    val position: Int,
    val time: String
)