package com.example.todolistapp.services

import retrofit2.Call
import com.example.todolistapp.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPIService{
    @POST("api/register")
    //body untuk send konten/data
    fun register(@Body registerMap: HashMap<String, String>): Call<UserResponse>

    @POST("api/loginregister")
    fun login(@Body registerMap: HashMap<String, String>): Call<UserResponse>
}