package com.example.todolistapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.todolistapp.repositories.AuthenticationRepository
import com.example.todolistapp.repositories.NetworkAuthenticationRepository
import com.example.todolistapp.services.AuthenticationAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val authenticationRepository: AuthenticationRepository
}

class DefaultAppContainer(private val userDataStore: DataStore<Preferences>): AppContainer {
    private val APIBaseURL = "http://192.168.176.50:3000/" //isi dengan IP address wifi kita JANGAN PAKAI WIFI UC KRN GABISA

    private val authenticationRetrofitService: AuthenticationAPIService by lazy {
        val retrofit = initRetrofit()

        retrofit.create(AuthenticationAPIService::class.java)
    }

    override val authenticationRepository: AuthenticationRepository by lazy {
        NetworkAuthenticationRepository(authenticationRetrofitService)
    }

    private fun initRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        return Retrofit
            .Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(client.build())
            .baseUrl(APIBaseURL)
            .build()
    }
}