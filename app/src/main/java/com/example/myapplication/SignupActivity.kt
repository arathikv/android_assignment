package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

val userArray = ArrayList<Users>()

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val signUp = findViewById<Button>(R.id.signUp)
        signUp.setOnClickListener() {

            val usernameEditText = findViewById<EditText>(R.id.username)
            val passwordEditText = findViewById<EditText>(R.id.password)
            val emailEditText = findViewById<EditText>(R.id.email)

            var username = usernameEditText.text.toString()
            var password = passwordEditText.text.toString()
            var email = emailEditText.text.toString()

            if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
            else {
                val random = Random
                val id = random.generateRandomId()
//                var id = generateRandomId()
                var user = Users(username, password, email, id)

                userArray.add(user)
                println("$userArray")
                Log.d("users", "$userArray")
            }
        }
    }



    fun Random.generateRandomId(): String {
        val allowedChars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..8)
            .map { allowedChars.random() }
            .joinToString("")

    }

//    fun generateRandomId(): Int {
//        val random = Random
//        val id = random.nextInt(1, 1000)
//        return id
//    }
}
