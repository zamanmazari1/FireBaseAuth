package com.example.tesfirebase.app.data.repository

import com.example.tesfirebase.app.data.model.request.LogInRequest
import com.example.tesfirebase.app.data.model.request.SignUpRequest
import com.example.tesfirebase.app.data.model.response.LogInResponse
import com.example.tesfirebase.app.data.model.response.SigUpResponse
import com.example.tesfirebase.app.data.services.authService
import com.example.tesfirebase.app.uites.DataState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class Repository : authService {
    private val auth = FirebaseAuth.getInstance()
    override suspend fun signUp(sigUprequest: SignUpRequest): DataState<SigUpResponse?> {
        return try {
            val response =
                auth.createUserWithEmailAndPassword(sigUprequest.email, sigUprequest.password).await()
            DataState.Success(SigUpResponse(id = response.user?.uid ?: "No token found"))

        } catch (e: Exception) {
            DataState.Error(e.message.toString())

        }
    }

    override suspend fun signIn(SignInRequest: LogInRequest): DataState<LogInResponse?> {
        return try {
            val response =
                auth.createUserWithEmailAndPassword(SignInRequest.email, SignInRequest.password).await()
            DataState.Success(LogInResponse(id = response.user?.uid ?: "No token found"))

        } catch (e: Exception) {
            DataState.Error(e.message.toString())

        }

    }

}