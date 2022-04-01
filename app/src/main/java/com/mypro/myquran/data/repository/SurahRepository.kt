package com.mypro.myquran.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.data.database.surah.SurahDao
import com.mypro.myquran.data.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.Flow

class SurahRepository(private val surahDao: SurahDao) {
    private val service = Api.retrofitService
    val surahs = surahDao.getSurahs().asLiveData()

    suspend fun getSurahs(){
        withContext(Dispatchers.IO){
            val surahs = service.getSurahs()
            surahDao.addSurahs(surahs)
        }
    }
}