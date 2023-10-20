package com.example.myapplication.modules.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.modules.home.HomeActivity
import com.example.myapplication.R
import com.example.myapplication.UsersViewModel
import com.example.myapplication.models.UsersApiData

const val BASE_URL = "https://dummyjson.com/"

class MainActivity2 : AppCompatActivity() {
    private lateinit var sh: SharedPreferences
    private lateinit var viewModel: UsersViewModel
    private lateinit var successImageView1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.button2)
        successImageView1 = findViewById(R.id.imageView1)

        sh = getSharedPreferences("SharedPref", MODE_PRIVATE)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)

        if (sh.contains("name")) {
            Intent(this, HomeActivity::class.java).run {
                startActivity(this)
            }
        }
        else {
            loginButton.setOnClickListener {
                val enteredUsername = usernameEditText.text.toString()
                val enteredPassword = passwordEditText.text.toString()
                println(enteredUsername + enteredPassword)
                viewModel.makeApiRequest(enteredUsername, enteredPassword)
                viewModel.liveData.observe(this) {
                    println(it)

                }

                val user = viewModel.liveData.value
                if (user != null) {
                    showToastAndNavigate(this@MainActivity2, "Login successful")
                    storeData(user)
                }
                else{
                    loginFailedToast()
                }
            }
        }

        val signUp = findViewById<TextView>(R.id.signUp)
        signUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToastAndNavigate(context: Context, message: String) {
        showToast(context, message)
        successImageView1.setImageResource(R.drawable.success_image)
        successImageView1.visibility = ImageView.VISIBLE
        Intent(context, HomeActivity::class.java).run {
            startActivity(this)
        }
    }

    private fun storeData(user: UsersApiData) {
        var editor = sh.edit()
        editor.putString("name", user.firstName)
        editor.putString("username", user.username)
        editor.putString("email", user.email)
        editor.apply()

    }

    private fun loginFailedToast() {
        runOnUiThread {
            showToast(this@MainActivity2, "Login failed.")
            successImageView1.setImageResource(R.drawable.unsuccess_image)
            successImageView1.visibility = ImageView.VISIBLE
        }
    }

}
//retrofit login validation
//    fun makeApiRequest(enteredUsername: String, enteredPassword: String) {
//        val retrofit= UsersApiHelper.getInstance()
//        val apiService = retrofit.create(UsersApiInterface::class.java)
//
//        val username1 = "kdulyt"
//        val password1 = "5t6q4KC7O"
//
//        val username2 = enteredUsername
//        val password2 = enteredPassword
//
//        println(username2 + password2)
//
//        val request = AuthenticationRequest(username2, password2)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val user = apiService.authenticate(request)
//                // Authentication successful, handle the user data
//                println("User details: $user")
//                launch(Dispatchers.Main) {
//                    showToastAndNavigate(this@MainActivity2,"Login successful",user)
//                    storeData(user)
//                }
//
//            } catch (e: Exception) {
//                // Authentication failed, handle the error
//                handleAuthenticationFailure(e)
//            }
//        }
//    }




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
