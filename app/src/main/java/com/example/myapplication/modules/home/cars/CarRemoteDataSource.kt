package com.example.myapplication.modules.home.cars

import com.example.myapplication.models.Car
import com.example.myapplication.network.CarHelper
import com.example.myapplication.network.CarInterface
import retrofit2.Response

class CarRemoteDataSource {
    private val retrofit = CarHelper.getInstance()

    private val apiService = retrofit.create(CarInterface::class.java)

    suspend fun fetchData(page:Int): Car {
        return apiService.getData(page)
    }
}
