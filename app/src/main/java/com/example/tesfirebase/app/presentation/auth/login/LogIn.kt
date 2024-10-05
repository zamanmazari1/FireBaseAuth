package com.example.tesfirebase.app.presentation.auth.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tesfirebase.app.appnavigation.auth_navigation.AppRoutes
import com.example.tesfirebase.app.data.model.request.LogInRequest
import com.example.tesfirebase.app.data.model.response.LogInResponse
import com.example.tesfirebase.app.data.repository.Repository
import com.example.tesfirebase.app.uites.DataState
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(vm: LogInViewModel = viewModel(),navController: NavController) {

    val context = LocalContext.current
    val loginState = vm.signInState.observeAsState()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        OutlinedTextField(
            value = vm.email.value,
            onValueChange = {
                vm.email.value = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Red
            ),
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Enter Your Email") },

            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = vm.password.value,
            onValueChange = {
                vm.password.value = it
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                focusedLabelColor = Color.Red,

            ),
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Enter Your Password") },

            )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { vm.LogIn() }) {
            Text(text = "Login")
        }
        Text(text = "or")
        Button(onClick = { navController.navigate(AppRoutes.Signup.name) }) {
            Text(text = "Sign Up")
        }

        when (val state = loginState.value) {
            is DataState.Loading -> {
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                vm.reset()
            }

            is DataState.Success<*> -> {
                Toast.makeText(
                    context,
                    "SuccessFull: ${state.data}",
                    Toast.LENGTH_SHORT
                ).show()
                vm.reset()
                navController.navigate(AppRoutes.Home.name)
            }

            is DataState.Error -> {
                Toast.makeText(context, "error :${state.errorMessage}", Toast.LENGTH_SHORT).show()
                vm.reset()
            }

            null -> {

            }
        }
    }
}


class LogInViewModel : ViewModel() {
    val signInState = MutableLiveData<DataState<LogInResponse?>?>(null)
    val repositry = Repository()
    fun reset() {
        signInState.value =null
    }

    val email = mutableStateOf("")
    val password = mutableStateOf("")
    fun LogIn() {
        viewModelScope.launch {
            signInState.value = DataState.Loading
            signInState.value = repositry.signIn(LogInRequest(email.value, password.value))

        }
    }
}