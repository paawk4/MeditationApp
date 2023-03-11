package com.example.meditationapp.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meditationapp.room.dao.FeelingsDao
import com.example.meditationapp.room.dao.ImageDao
import com.example.meditationapp.room.dao.QuotesDao
import com.example.meditationapp.room.dao.UserDao
import com.example.meditationapp.room.entities.FeelingEntity
import com.example.meditationapp.room.entities.ImageEntity
import com.example.meditationapp.room.entities.QuoteEntity
import com.example.meditationapp.room.entities.UserEntity

@Database(
    entities = [UserEntity::class, QuoteEntity::class, FeelingEntity::class, ImageEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2)]
)
abstract class MeditationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun feelingsDao(): FeelingsDao
    abstract fun quotesDao(): QuotesDao
    abstract fun imageDao(): ImageDao
}