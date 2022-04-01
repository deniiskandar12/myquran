package com.mypro.myquran.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.data.database.surah.SurahDao

@Database(entities = [Surah::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun surahDao(): SurahDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}