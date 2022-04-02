package com.mypro.myquran.ui.module.detail_surah

import android.app.Application
import androidx.lifecycle.*
import com.mypro.myquran.data.database.AppDatabase
import com.mypro.myquran.data.database.detail_surah.DetailSurah
import com.mypro.myquran.data.network.ApiStatus
import com.mypro.myquran.data.repository.DetailSurahRepository
import kotlinx.coroutines.launch

class DetailSurahViewModel(application: Application) : AndroidViewModel(application) {
    private val repository =
        DetailSurahRepository(AppDatabase.getDatabase(application).detailSurahDao())

    var detailSurahs: LiveData<List<DetailSurah>>? = null
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    fun fetchDetailSurahs(number: Int) {
        detailSurahs = repository.getDetailSurahsFromDatabase(number).asLiveData()
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                repository.getDetailSurahs(number)
                _status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
            }
            if (detailSurahs?.value?.isNotEmpty() == true)
                _status.value = ApiStatus.SUCCESS
        }
    }

    val showData = fun() {
        _status.value = ApiStatus.SUCCESS
    }
}

class DetailSurahViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailSurahViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailSurahViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}