package com.example.myapplication.network

import retrofit2.Response
import retrofit2.http.GET

interface QuotesServiceInterface {
    @GET("/v2/quotes/")
    suspend fun getQuotes():List<String>
}