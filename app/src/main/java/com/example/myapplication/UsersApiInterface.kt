package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersApiInterface {
    @POST("/auth/login")
    suspend fun authenticate(@Body request: AuthenticationRequest): UsersApiData
}