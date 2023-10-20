package com.example.myapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Car
import com.example.myapplication.network.CarHelper
import com.example.myapplication.network.CarInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarViewModel : ViewModel() {
    var carLiveData: MutableLiveData<Car> = MutableLiveData()

    fun makeCarApiCall() {
        val retroInstance = CarHelper.getInstance()
        val apiservice = retroInstance.create(CarInterface::class.java)
        viewModelScope.launch {
            val response = apiservice.getData()
            carLiveData.value = response
        }
    }
}