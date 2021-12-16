package com.example.fixerapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "http://data.fixer.io/api/"
private const val KEY = "442af56b37d48903672db189745b7f4b"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface FixerApiService {
    @GET("{date}")
    suspend fun getData(
        @Path("date") date: String,
        @Query("access_key") access_key: String = KEY,
        @Query("format") format: String = "1")
    : FixerData
}

object FixerApi{
    val retrofitService: FixerApiService by lazy{
        retrofit.create(FixerApiService::class.java)
    }
}