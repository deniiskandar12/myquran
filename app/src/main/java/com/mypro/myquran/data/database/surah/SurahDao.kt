package com.mypro.myquran.data.database.surah

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SurahDao {
    @Query("SELECT * FROM surah")
    fun getSurahs(): Flow<List<Surah>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSurahs(surahs : List<Surah>)
}