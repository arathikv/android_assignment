package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.models.AuthenticationRequest
import com.example.myapplication.models.UsersApiData
import com.example.myapplication.network.UsersApiHelper
import com.example.myapplication.network.UsersApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    var liveData: MutableLiveData<UsersApiData> = MutableLiveData()
    val readUser : LiveData<UsersApiData> get() = liveData
    fun getLiveDataObserver():MutableLiveData<UsersApiData>{
        return liveData
    }

//    fun makeAPICall(){
//        val retroInstance = UsersApiHelper.getInstance()
//        val retroInterface=retroInstance.create(UsersApiInterface::class.java)
//
//
//    }
    fun makeApiRequest(enteredUsername: String, enteredPassword: String) {
        val retroInstance = UsersApiHelper.getInstance()
        val retroInterface=retroInstance.create(UsersApiInterface::class.java)
        val retrofit=UsersApiHelper.getInstance()
        val apiService = retrofit.create(UsersApiInterface::class.java)

//        val username1 = "kdulyt"
//        val password1 = "5t6q4KC7O"
//
        val username2 = enteredUsername
        val password2 = enteredPassword
//
//        println(username2 + password2)
//
        val request = AuthenticationRequest(username2, password2)
//

         viewModelScope.launch() {
            try {
                val user = apiService.authenticate(request)
                println(user)
                // Authentication successful, handle the user data
                liveData.value=user
                println("User details: $user")


            } catch (e: Exception) {
                liveData.value=null
                // Authentication failed, handle the error
//                handleAuthenticationFailure(e)
            }
        }

}


}