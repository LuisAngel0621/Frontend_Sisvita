package com.example.proyecto_sisvita.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://backend-sisvita-93yl.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInstance by lazy{
        retrofit.create(ApiService::class.java)
    }
}