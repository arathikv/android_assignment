package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET

interface CarInterface {
    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getData(): Car
}