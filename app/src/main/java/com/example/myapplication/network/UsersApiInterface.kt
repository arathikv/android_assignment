package com.example.myapplication.network

import com.example.myapplication.models.AuthenticationRequest
import com.example.myapplication.models.UsersApiData
import retrofit2.http.Body
import retrofit2.http.POST

interface UsersApiInterface {
    @POST("/auth/login")
    suspend fun authenticate(@Body request: AuthenticationRequest): UsersApiData
}