package com.example.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QuotesHelper {
    private const val BASE_URL_QUOTES = "https://ron-swanson-quotes.herokuapp.com/"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_QUOTES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}