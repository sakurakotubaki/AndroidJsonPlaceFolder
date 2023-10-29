package com.example.httpgetexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.httpgetexample.repository.UserRepo
import com.example.httpgetexample.retrofit.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(val userRepo: UserRepo): ViewModel() {

    private  val _state = MutableStateFlow(emptyList<User>())

    val state: StateFlow<List<User>>
        get() = _state

    init{
        viewModelScope.launch {
            val users = userRepo.getUsers()
            _state.value = users
        }
    }
}