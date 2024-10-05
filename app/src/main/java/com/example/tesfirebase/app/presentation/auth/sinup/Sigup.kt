package com.example.tesfirebase.app.presentation.auth.sinup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tesfirebase.app.uites.DataState

@Composable
fun SignupScreen(vm: viewmodelsigup = viewModel(),navController: NavController) {

    val context = LocalContext.current
    val sigupState = vm.sigupState.observeAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = vm.email.value, onValueChange ={
            vm.email.value = it
        } )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = vm.password.value, onValueChange = {
            vm.password.value = it
            vm.reset()
        })
        Spacer(modifier = Modifier.height(16.dp))

        androidx.compose.material3.Button(onClick = {
            vm.signUp()

        }) {
            Text(text = "Sign Up")
        }

        when (val state = sigupState.value) {
            is DataState.Loading -> {
                Toast.makeText(context, "Loading.....", Toast.LENGTH_SHORT).show()
                vm.reset()
            }
            is DataState.Success -> {
                Toast.makeText(context, "Successful:${state.data}", Toast.LENGTH_SHORT).show()
                vm.reset()
                navController.navigate("login")
            }

            is DataState.Error -> {
                Toast.makeText(context, state.errorMessage.toString(), Toast.LENGTH_SHORT).show()
                vm.reset()
            }

            null -> {}

        }
    }
}



//sealed class DataState<out T> {
//    object Loading : DataState<Nothing>()
//    data class Success<T>(val data: T) : DataState<T>()
//    data class Error(val errorMessage: Any) : DataState<Nothing>()
//
//
//}
//
//data class SignUpRequest(
//    val email: String,
//    val password: String
//)
//
//data class SignInRequest(
//    val email: String,
//    val password: String
//)
//
//data class SignUpResponse(
//    val id: String
//)
//
//data class SignInResponse(
//    val id: String
//)
//
///////////////////////////////////////////Auth service///////////////////////////////////
//interface AuthService {
//    suspend fun signup(signUpRequest: SignUpRequest): DataState<SigUpResponse?>
//    suspend fun signin(signInRequest: LogInRequest): DataState<LogInResponse?>
//}
//
//class Repository : authService {
//    private val auth = FirebaseAuth.getInstance()
//
//    override suspend fun signUp(sigUprequest: SignUpRequest): DataState<SigUpResponse?> {
//        return try {
//            val response = auth.createUserWithEmailAndPassword(
//                sigUprequest.email,
//                sigUprequest.password
//            ).await()
//            DataState.Success(SigUpResponse(id = response.user?.uid ?: "no token"))
//        } catch (e: Exception) {
//            DataState.Error(e.message.toString())
//
//        }
//
//    }
//
//    override suspend fun signIn(SignInRequest: LogInRequest): DataState<LogInResponse?> {
//        return try {
//            val response = auth.signInWithEmailAndPassword(
//                SignInRequest.email,
//                SignInRequest.password
//            ).await()
//            DataState.Success(LogInResponse(id = response.user?.uid ?: "no token"))
//        } catch (e: Exception) {
//            DataState.Error(e.message.toString())
//
//        }
//    }
//}
//
//
//class signUpViewModel : ViewModel() {
//    val signUpState = MutableLiveData<DataState<SigUpResponse?>?>(null)
//    val repositry = Repository()
//    fun reset() {
//        signUpState.value = null
//    }
//
//    val email = mutableStateOf("")
//    val password = mutableStateOf("")
//    fun signup() {
//        viewModelScope.launch {
//            signUpState.value = DataState.Loading
//            signUpState.value = repositry.signUp(SignUpRequest(email.value, password.value))
//
//        }
//    }
//}
//
//@Composable
//fun logInVm(vm: signUpViewModel = signUpViewModel()) {
//    val context = LocalContext.current
//    val signState = vm.signUpState.observeAsState()
//    Column(
//        Modifier
//            .background(Color.LightGray)
//            .fillMaxSize()
//            .padding(20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        OutlinedTextField(
//            value = vm.email.value,
//            onValueChange = {
//                vm.email.value = it
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                focusedTextColor = Color.Black,
//                focusedLabelColor = Color.Red
//            ),
//            label = { Text(text = "Email") },
//            placeholder = { Text(text = "Enter Your Email") },
//
//            )
//        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(
//            value = vm.password.value,
//
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                focusedTextColor = Color.Black,
//                focusedLabelColor = Color.Red,
//
//                ),
//            onValueChange = {
//                vm.password.value = it
//            },
//            label = { Text(text = "PassWord") },
//            placeholder = { Text(text = "Enter Your Password") },
//
//            )
//        Spacer(modifier = Modifier.height(20.dp))
//        Button(onClick = { vm.signup() }) {
//            Text(text = "LogIn")
//        }
//        when (val state = signState.value) {
//            is DataState.Loading -> {
//                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
//                vm.reset()
//            }
//
//            is DataState.Success<*> -> {
//                Toast.makeText(
//                    context,
//                    "SuccessFull: ${state.data}",
//                    Toast.LENGTH_SHORT
//                ).show()
//                vm.reset()
//            }
//
//            is DataState.Error -> {
//                Toast.makeText(context, "error :${state.errorMessage}", Toast.LENGTH_SHORT).show()
//                vm.reset()
//            }
//
//            null -> {
//
//            }
//        }
//    }
//}
//
//class signInViewModel : ViewModel() {
//    val signInState = MutableLiveData<DataState<SignInResponse?>>(null)
//    val repositry = Repository()
//    fun reset() {
//        signInState.value = null
//    }
//
//    val email = mutableStateOf("")
//    val password = mutableStateOf("")
//    fun signIn() {
//        viewModelScope.launch {
//            signInState.value = DataState.Loading
//            signInState.value = repositry.signin(SignInRequest(email.value, password.value))
//
//        }
//    }
//}
//@Composable
//fun sigInVm(vm: signInViewModel = viewModel()) {
//    val context = LocalContext.current
//    val signState = vm.signInState.observeAsState()
//    Column(
//        Modifier
//            .background(Color.LightGray)
//            .fillMaxSize()
//            .padding(20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        OutlinedTextField(
//            value = vm.email.value,
//            onValueChange = {
//                vm.email.value = it
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                focusedTextColor = Color.Black,
//                focusedLabelColor = Color.Red
//            ),
//            label = { Text(text = "Email") },
//            placeholder = { Text(text = "Enter Your Email") },
//
//            )
//        Spacer(modifier = Modifier.height(20.dp))
//        OutlinedTextField(
//            value = vm.password.value,
//
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                focusedTextColor = Color.Black,
//                focusedLabelColor = Color.Red,
//
//                ),
//            onValueChange = {
//                vm.password.value = it
//            },
//            label = { Text(text = "PassWord") },
//            placeholder = { Text(text = "Enter Your Password") },
//
//            )
//        Spacer(modifier = Modifier.height(20.dp))
//        Button(onClick = { vm.signIn() }) {
//            Text(text = "LogIn")
//        }
//        when (val state = signState.value) {
//            is DataState.Loading -> {
//                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
//                vm.reset()
//            }
//
//            is DataState.Success<*> -> {
//                Toast.makeText(
//                    context,
//                    "SuccessFull: ${state.data}",
//                    Toast.LENGTH_SHORT
//                ).show()
//                vm.reset()
//            }
//
//            is DataState.Error -> {
//                Toast.makeText(context, "error :${state.errorMessage}", Toast.LENGTH_SHORT).show()
//                vm.reset()
//            }
//
//            null -> {
//
//            }
//        }
//    }
//}