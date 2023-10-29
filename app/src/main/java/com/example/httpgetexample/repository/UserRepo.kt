package com.example.httpgetexample.repository

import com.example.httpgetexample.retrofit.User
import com.example.httpgetexample.retrofit.UserApi

class UserRepo(private  val userApi: UserApi) {

        suspend fun getUsers(): List<User>{
            return userApi.getUsers()
        }
}