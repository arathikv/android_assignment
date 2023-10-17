package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL1 = "https://ron-swanson-quotes.herokuapp.com/"


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val receivedData = intent.getStringExtra("message")
        val welcome = findViewById<TextView>(R.id.welcome)
        welcome.setText("Welcome, $receivedData")


        val frag1 = Example1Fragment.newInstance("frag1", "frag2")
        val frag2 = Example2Fragment.newInstance()
        val frag3 = Example3Fragment.newInstance()
        val frag4 = Example4Fragment.newInstance()



        supportFragmentManager.beginTransaction()
            .add(R.id.frame1, frag1, "tag_frag1")
            .commit()

        val button_1 = findViewById<Button>(R.id.button_1)
        val button_2 = findViewById<Button>(R.id.button_2)
        val button_3 = findViewById<Button>(R.id.button_3)
        val button_4 = findViewById<Button>(R.id.button_4)



        button_1.setOnClickListener() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, frag1, "tag_frag1")
                .commit()
        }
        button_2.setOnClickListener() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, frag2, "tag_frag1")
                .commit()
        }
        button_3.setOnClickListener() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, frag3, "tag_frag3")
                .commit()
        }
        button_4.setOnClickListener() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame1, frag4, "tag_frag3")
                .commit()
        }

        getQuotes()
    }

    private fun getQuotes() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL1)
            .build()
            .create(QuotesInterface::class.java)
        val qq = findViewById<TextView>(R.id.q1)
        GlobalScope.launch {
            val retrofitData1 = retrofitBuilder.getQuotes()
            val data = retrofitData1.body()?.first()
            println(data)
            if (data != null && data.isNotEmpty()) {
                runOnUiThread {
                    qq.text = data

                }
            }

        }
    }


}