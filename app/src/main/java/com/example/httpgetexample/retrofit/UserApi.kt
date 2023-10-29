package com.example.httpgetexample.retrofit

import retrofit2.http.GET

interface UserApi {

    @GET("users") // END-POINT
    suspend fun getUsers(): List<User>
}