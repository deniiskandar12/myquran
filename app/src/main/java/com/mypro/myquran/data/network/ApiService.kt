package com.mypro.myquran.data.network

import com.mypro.myquran.data.database.detail_surah.DetailSurah
import com.mypro.myquran.data.database.surah.Surah
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://api.npoint.io/99c279bb173a6e28359c/"

interface ApiService {
    @GET("data")
    suspend fun getSurahs(): List<Surah>

    @GET("surat/{nomor}")
    suspend fun getDetailSurahs(
        @Path("nomor") number : Int
    ): List<DetailSurah>
}

object Api {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}