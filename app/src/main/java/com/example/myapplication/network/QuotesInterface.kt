package com.example.myapplication.network

import retrofit2.Response
import retrofit2.http.GET

interface QuotesInterface {
    @GET("/v2/quotes/")
    suspend fun getQuotes():Response<List<String>>
}