package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val receivedData=intent.getStringExtra("message")
        val welcome=findViewById<TextView>(R.id.welcome)
        welcome.setText("Welcome, $receivedData")
    }
}