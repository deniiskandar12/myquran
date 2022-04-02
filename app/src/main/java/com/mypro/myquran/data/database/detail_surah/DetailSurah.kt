package com.mypro.myquran.data.database.detail_surah

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "detail_surah", primaryKeys = ["id", "surah_id"])
data class DetailSurah(
    @Json(name="nomor")
    val id: Int ,
    @ColumnInfo(name = "surah_id")
    val surahId: Int = 0,
    @Json(name="ar")
    val ayat: String,
    @Json(name = "id")
    val translation : String
)