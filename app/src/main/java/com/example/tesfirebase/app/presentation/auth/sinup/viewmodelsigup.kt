package com.example.tesfirebase.app.presentation.auth.sinup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tesfirebase.app.data.model.request.SignUpRequest
import com.example.tesfirebase.app.data.model.response.SigUpResponse
import com.example.tesfirebase.app.data.repository.Repository
import com.example.tesfirebase.app.uites.DataState
import kotlinx.coroutines.launch


//
//class viewmodelsigup: ViewModel() {
//val signupState = MutableLiveData<DataState<SigUpResponse?>?>(null)
//    val email= mutableStateOf("")
//    val password = mutableStateOf("")
//val repository = Repository()
//    fun signUp(){
//        viewModelScope.launch {
//            signupState.value = DataState.Loading
//            signupState.value = repository.signUp(SignUpRequest(email.value, password.value))
//        }
//    }
//
//}




class viewmodelsigup: ViewModel(){
    val  sigupState =MutableLiveData<DataState<SigUpResponse?>?>(null)
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    val repository = Repository()
    fun reset(){
        sigupState.value = null
    }

    fun signUp(){
        viewModelScope.launch {
            sigupState.value = DataState.Loading
            sigupState.value = repository.signUp(SignUpRequest(email.value,password.value))
        }
    }
}
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