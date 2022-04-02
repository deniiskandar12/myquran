package com.mypro.myquran.data.repository

import com.mypro.myquran.data.database.detail_surah.DetailSurah
import com.mypro.myquran.data.database.detail_surah.DetailSurahDao
import com.mypro.myquran.data.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DetailSurahRepository(private val detailSurahDao: DetailSurahDao) {
    private val service = Api.retrofitService

    suspend fun getDetailSurahs(number: Int) {
        withContext(Dispatchers.IO) {
            val detailSurahs = service.getDetailSurahs(number).toMutableList()
            detailSurahs.forEachIndexed { index, detailSurah ->
                detailSurahs[index] = detailSurah.copy(surahId = number)
            }
            detailSurahDao.addDetailSurahs(detailSurahs)
        }
    }

    fun getDetailSurahsFromDatabase(number: Int): Flow<List<DetailSurah>> {
        return detailSurahDao.getDetailSurahs(number)
    }
}