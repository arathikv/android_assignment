package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.QuotesHelper
import com.example.myapplication.network.QuotesServiceInterface
import kotlinx.coroutines.launch

class QuotesViewModel : ViewModel() {
    var quotesLiveData: MutableLiveData<List<String>> = MutableLiveData()
    fun getQuotes() {
        val retroInstance = QuotesHelper.getInstance()
        val retroService = retroInstance.create(QuotesServiceInterface::class.java)
        viewModelScope.launch {
            val quotes = retroService.getQuotes()
            quotesLiveData.value = quotes
        }
    }
}