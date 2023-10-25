package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.Car
import com.example.myapplication.models.CarResult
import com.example.myapplication.network.CarHelper
import com.example.myapplication.network.CarInterface
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

//class CarViewModel : ViewModel() {
//    private var currentPage = 1
//    private val pageSize = 5
//    var isLoading = false
//    var isLastPage = false
//
//    var carLiveData: MutableLiveData<MutableList<CarResult>> = MutableLiveData()
//
//    fun makeCarApiCall() {
//        if (isLoading || isLastPage) {
//            return
//        }
//
//        isLoading = true
//
//        val retroInstance = CarHelper.getInstance()
//        val apiservice = retroInstance.create(CarInterface::class.java)
//
//        viewModelScope.launch {
//            try {
//                val response = apiservice.getData()
//
//
//                    val count = response.Count?: 0
//                    val data = response?.Results ?: emptyList()
//
//                if (data.isNotEmpty()) {
//                    carLiveData.postValue(data.toMutableList())
//                    currentPage++ // Move to the next page
//                } else {
//                    isLastPage = true
//                }
//                    // You can now use 'count' and 'data' as needed
//
//                    // Slice the data into pages (if you want to implement client-side pagination)
//                    val pages = data.chunked(pageSize)
//
//                    // ...
//
//            } catch (e: Exception) {
//                // Handle the error here
//                Log.e("CarViewModel", "Error fetching data: ${e.message}")
//            } finally {
//                isLoading = false
//            }
//        }
//    }
//}
