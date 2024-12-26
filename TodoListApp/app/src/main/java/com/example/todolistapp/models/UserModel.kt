package com.example.todolistapp.models

data class UserResponse (
    val data: UserModel
)

data class UserModel (
    val username: String,
    val token: String?

    //kedua data ini didapat dari backendnya (apa yang direturn oleh backend)
    //Mengapa UserModel masih harus dibungkus oleh UserResponse?
    // > Karena return dari backend adalah data User yang dibungkus oleh variabel data
    //{
    //    data: {
    //        username: String,
    //        token: String?
    //    }
    //}
)