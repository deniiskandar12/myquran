package com.mypro.myquran.data.database.detail_surah

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailSurahDao {
    @Query("SELECT * FROM detail_surah WHERE surah_id = :surahId")
    fun getDetailSurahs(surahId: Int): Flow<List<DetailSurah>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDetailSurahs(detailSurahs: List<DetailSurah>) : List<Long>
}