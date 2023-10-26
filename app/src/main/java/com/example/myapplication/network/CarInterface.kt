package com.example.myapplication.network

import com.example.myapplication.models.Car
import retrofit2.http.GET
import retrofit2.http.Query

interface CarInterface {
    @GET("/api/vehicles/getallmanufacturers?format=json")
//    suspend fun getData(): Car

    suspend fun getData(@Query("page") page: Int): Car
}