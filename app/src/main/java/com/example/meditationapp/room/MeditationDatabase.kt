package com.example.meditationapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meditationapp.room.dao.FeelingsDao
import com.example.meditationapp.room.dao.QuotesDao
import com.example.meditationapp.room.dao.UserDao
import com.example.meditationapp.room.entities.FeelingEntity
import com.example.meditationapp.room.entities.QuoteEntity
import com.example.meditationapp.room.entities.UserEntity

@Database(entities = [UserEntity::class, QuoteEntity::class, FeelingEntity::class], version = 1)
abstract class MeditationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun feelingsDao(): FeelingsDao
    abstract fun quotesDao(): QuotesDao
}