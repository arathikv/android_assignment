package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiInterface {
    @GET("/users")
    fun getData():Call<List<UsersApi>>
}