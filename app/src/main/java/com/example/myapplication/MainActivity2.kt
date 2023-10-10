package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.button2)
        loginButton.setOnClickListener {
            val enteredUsername = usernameEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            validate(enteredUsername, enteredPassword)
        }
    }

    fun validate(username: String, password: String) {

        val username1 = "Admin"
        val password1 = "123"
        val successImageView1 = findViewById<ImageView>(R.id.imageView1)

        if (username1.equals(username) and password1.equals(password)) {
            showToast("Login Successful")
            successImageView1.setImageResource(R.drawable.success_image)
            successImageView1.visibility = ImageView.VISIBLE

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            showToast("Login Failed")
            successImageView1.setImageResource(R.drawable.unsuccess_image)
            successImageView1.visibility = ImageView.VISIBLE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
