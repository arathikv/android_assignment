package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Example2Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        val qq = findViewById<TextView>(R.id.quotes)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example2, container, false)
    }

    companion object {
        @JvmStatic fun newInstance() = Example2Fragment()
    }
    private fun getQuotes() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL1)
            .build()
            .create(QuotesInterface::class.java)
        GlobalScope.launch {
            val retrofitData1 = retrofitBuilder.getQuotes()
            val data = retrofitData1.body()?.first()
            println(data)
            if (data != null && data.isNotEmpty()) {
//                runOnUiThread {
//                    qq.text = data
//
//                }
            }

        }
    }

}