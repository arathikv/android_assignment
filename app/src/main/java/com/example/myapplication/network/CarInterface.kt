package com.example.myapplication.network

import com.example.myapplication.models.Car
import retrofit2.http.GET

interface CarInterface {
    @GET("/api/vehicles/getallmanufacturers?format=json")
    suspend fun getData(): Car
}