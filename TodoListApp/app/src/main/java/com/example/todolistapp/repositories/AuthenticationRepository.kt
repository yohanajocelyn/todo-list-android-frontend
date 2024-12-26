package com.example.todolistapp.repositories

import com.example.todolistapp.models.UserResponse
import com.example.todolistapp.services.AuthenticationAPIService
import retrofit2.Call

//AuthenticationRepository.kt
interface AuthenticationRepository{
    fun register(username: String, email: String, password: String): Call<UserResponse>
    fun login(username: String, password: String): Call<UserResponse>
}

class NetworkAuthenticationRepository(
    private val authenticationAPIService: AuthenticationAPIService
): AuthenticationRepository {
    override fun register(username: String, email: String, password: String): Call<UserResponse> {
        var registerMap = HashMap<String, String>()

        registerMap["username"] = username
        registerMap["email"] = email
        registerMap["password"] = password

        return authenticationAPIService.register(registerMap)
    }

    override fun login(username: String, password: String): Call<UserResponse> {
        TODO("Not yet implemented")
    }

}