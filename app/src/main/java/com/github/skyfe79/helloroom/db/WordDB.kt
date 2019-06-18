package com.github.skyfe79.helloroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDB: RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordDB? = null

        fun getInstance(context: Context): WordDB {
            return INSTANCE ?: synchronized(this) {
                val db = Room.databaseBuilder(context.applicationContext,
                    WordDB::class.java,
                    "words.db"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}