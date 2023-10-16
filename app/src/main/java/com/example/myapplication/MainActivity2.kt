package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL="https://dummyjson.com/"

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

        val signUp = findViewById<TextView>(R.id.signUp)
        signUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }


    fun validate(username: String, password: String) {

        val successImageView1 = findViewById<ImageView>(R.id.imageView1)
        var user:UserAccount?=null
        if(username==UserAccount.Admin.name) {
             user = UserAccount.Admin
        }
        else if(username==UserAccount.User.name){
            user=UserAccount.User
        }

        var passwordEnum = user?.password

        if (passwordEnum.equals(password)) {

            showToast("Login Successful")
            successImageView1.setImageResource(R.drawable.success_image)
            successImageView1.visibility = ImageView.VISIBLE

            Intent(this, HomeActivity::class.java).run {
            putExtra("message", "$username")
            startActivity(this)
            }
        }
         else {
            showToast("Login Failed")
            successImageView1.setImageResource(R.drawable.unsuccess_image)
            successImageView1.visibility = ImageView.VISIBLE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    //retrofit

















//
//    fun Toast.setColor(){
//        val this.textColor="#"
//    }
}
