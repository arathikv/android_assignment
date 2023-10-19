package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dummyjson.com/"

class MainActivity2 : AppCompatActivity() {
    private lateinit var successImageView1: ImageView
    private lateinit var sh :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.button2)
        successImageView1 = findViewById(R.id.imageView1)

        sh=getSharedPreferences("SharedPref", MODE_PRIVATE)


        if (sh.contains("name")) {
            Intent(this, HomeActivity::class.java).run {
                startActivity(this)
            }
        }
        else {
            loginButton.setOnClickListener {
                val enteredUsername = usernameEditText.text.toString()
                val enteredPassword = passwordEditText.text.toString()
                makeApiRequest(enteredUsername, enteredPassword)
                //            validate(enteredUsername, enteredPassword)
            }
        }

        val signUp = findViewById<TextView>(R.id.signUp)
        signUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }


//    fun validate(username: String, password: String) {
//
//        val successImageView1 = findViewById<ImageView>(R.id.imageView1)
//        var user:UserAccount?=null
//        if(username==UserAccount.Admin.name) {
//             user = UserAccount.Admin
//        }
//        else if(username==UserAccount.User.name){
//            user=UserAccount.User
//        }
//
//        var passwordEnum = user?.password
//
//        if (passwordEnum.equals(password)) {
//
//            showToast("Login Successful")
//            successImageView1.setImageResource(R.drawable.success_image)
//            successImageView1.visibility = ImageView.VISIBLE
//
//            Intent(this, HomeActivity::class.java).run {
//            putExtra("message", "$username")
//            startActivity(this)
//            }
//        }
//         else {
//            showToast("Login Failed")
//            successImageView1.setImageResource(R.drawable.unsuccess_image)
//            successImageView1.visibility = ImageView.VISIBLE
//        }
//    }

    private fun showToast(context: Context,message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToastAndNavigate(context: Context, message: String, user: UsersApiData,) {
        showToast(context, message)
        successImageView1.setImageResource(R.drawable.success_image)
        successImageView1.visibility = ImageView.VISIBLE
        // Create an Intent to navigate to another activity or fragment
        Intent(context, HomeActivity::class.java).run {
            putExtra("message", user.username)
            startActivity(this)
        } // Replace with the actual activity or fragment you want to navigate to
//        context.startActivity(intent)
    }

    //retrofit login validation
    fun makeApiRequest(enteredUsername: String, enteredPassword: String) {
        val retrofit=UsersApiHelper.getInstance()
        val apiService = retrofit.create(UsersApiInterface::class.java)

        val username1 = "kdulyt"
        val password1 = "5t6q4KC7O"

        val username2 = enteredUsername
        val password2 = enteredPassword

        println(username2 + password2)

        val request = AuthenticationRequest(username2, password2)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val user = apiService.authenticate(request)
                // Authentication successful, handle the user data
                println("User details: $user")
                launch(Dispatchers.Main) {
                    showToastAndNavigate(this@MainActivity2,"Login successful",user)
                    storeData(user)
                }

            } catch (e: Exception) {
                // Authentication failed, handle the error
                handleAuthenticationFailure(e)
            }
        }
    }
    private fun storeData(user: UsersApiData) {
        var editor= sh.edit()
        editor.putString("name",user.firstName)
        editor.putString("username",user.username)
        editor.putString("email",user.email)
        editor.apply()

    }
    private fun handleAuthenticationFailure(exception: Exception) {
        println("Authentication failed. Error: ${exception.message}")

        runOnUiThread {
            showToast(this@MainActivity2, "Login failed.")
            successImageView1.setImageResource(R.drawable.unsuccess_image)
            successImageView1.visibility = ImageView.VISIBLE
        }
    }

}
