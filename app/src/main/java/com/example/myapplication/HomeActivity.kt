package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val receivedData=intent.getStringExtra("message")
        val welcome=findViewById<TextView>(R.id.welcome)
        welcome.setText("Welcome, $receivedData")

        val frag1=Example1Fragment.newInstance("frag1","frag2")
        val frag2=Example2Fragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame1,frag1,"tag_frag1")
            .commit()

        val button_1=findViewById<Button>(R.id.button_1)
        val button_2=findViewById<Button>(R.id.button_2)

        button_1.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame1,frag1,"tag_frag1")
                .commit()
        }
        button_2.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame1,frag2,"tag_frag1")
                .commit()
        }


    }




}