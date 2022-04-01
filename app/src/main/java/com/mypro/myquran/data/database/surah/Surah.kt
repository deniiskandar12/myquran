package com.mypro.myquran.data.database.surah

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Surah(
    @Json(name = "nomor")
    @PrimaryKey
    val id: Int,
    @Json(name = "nama")
    val name: String,
    @Json(name = "arti")
    val translation: String,
    val ayat: Int,
    val audio: String,
    @Json(name = "keterangan")
    val description: String
) : Parcelable