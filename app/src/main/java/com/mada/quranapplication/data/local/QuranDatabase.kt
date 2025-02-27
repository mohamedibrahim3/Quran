package com.mada.quranapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mada.quranapplication.data.local.dao.SurahDao
import com.mada.quranapplication.data.local.entity.SurahEntity


@Database(entities = [SurahEntity::class], version = 1, exportSchema = false)
abstract class QuranDatabase: RoomDatabase() {
    abstract fun surahDao(): SurahDao

    companion object{
        @Volatile
        private var INSTANCE: QuranDatabase? = null

        fun getDatabase(context: Context): QuranDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuranDatabase::class.java,
                    "quran_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}