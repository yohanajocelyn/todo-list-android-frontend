package com.example.todolistapp.services

import com.example.todolistapp.models.GeneralResponseModel
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAPIService {
    @POST("api/logout")
    //header untuk minta token
    //kirim token untuk diproses lewat header
    fun logout(@Header("X-API_TOKEN") token: String): Call<GeneralResponseModel>
}