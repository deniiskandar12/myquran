package com.mypro.myquran.ui.module.surah

import android.app.Application
import androidx.lifecycle.*
import com.mypro.myquran.data.database.AppDatabase
import com.mypro.myquran.data.database.surah.Surah
import com.mypro.myquran.data.network.ApiStatus
import com.mypro.myquran.data.repository.SurahRepository
import kotlinx.coroutines.launch
import java.lang.Exception


class SurahViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SurahRepository(AppDatabase.getDatabase(application).surahDao())
    val surahs = repository.surahs
    private val _status = MutableLiveData<ApiStatus>()
    val status : LiveData<ApiStatus> = _status

    init {
        fetchSurahs()
    }

    private fun fetchSurahs(){
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try{
                repository.getSurahs()
                _status.value = ApiStatus.SUCCESS
            }catch (e : Exception){
                _status.value = ApiStatus.ERROR
            }
            if (surahs.value?.isNotEmpty() == true)
                _status.value = ApiStatus.SUCCESS
        }
    }
}

class SurahViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SurahViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SurahViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}