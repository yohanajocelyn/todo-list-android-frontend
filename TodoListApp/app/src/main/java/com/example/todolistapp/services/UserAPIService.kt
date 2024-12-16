package com.example.todolistapp.services

import com.example.todolistapp.models.GeneralResponseModel
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPIService {
    @POST("api/logout")
    fun logout(@Header("X-API-TOKEN") token: String): Call<GeneralResponseModel>
}