package com.example.httpgetexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.httpgetexample.repository.UserRepo
import com.example.httpgetexample.retrofit.RetrofitInstance
import com.example.httpgetexample.retrofit.User
import com.example.httpgetexample.ui.theme.HttpGetExampleTheme
import com.example.httpgetexample.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HttpGetExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userApi = RetrofitInstance.provideApi(RetrofitInstance.provideRetrofit())

                    val userRepo = UserRepo(userApi)
                    val userViewModel = UserViewModel(userRepo)

                    MainScreen(userViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: UserViewModel) {
    val users by viewModel.state.collectAsState()

       UserList(users)
}

@Composable
fun UserList(users: List<User>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(users) { user ->
            Text(
                text = user.name,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = user.email,
                fontSize = 20.sp
            )
        }
    }
}