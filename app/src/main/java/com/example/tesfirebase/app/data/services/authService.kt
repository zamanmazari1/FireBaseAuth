package com.example.tesfirebase.app.data.services

import com.example.tesfirebase.app.data.model.request.LogInRequest
import com.example.tesfirebase.app.data.model.request.SignUpRequest
import com.example.tesfirebase.app.data.model.response.LogInResponse
import com.example.tesfirebase.app.data.model.response.SigUpResponse
import com.example.tesfirebase.app.uites.DataState


interface authService {
    suspend fun signUp(sigUprequest: SignUpRequest): DataState<SigUpResponse?>
    suspend fun signIn(SignInRequest: LogInRequest): DataState<LogInResponse?>

}