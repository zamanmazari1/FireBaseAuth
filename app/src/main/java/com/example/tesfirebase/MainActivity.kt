package com.example.tesfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavGraphBuilder
import com.example.tesfirebase.app.appnavigation.auth_navigation.AuthNavigation
import com.example.tesfirebase.app.presentation.auth.login.LoginScreen
import com.example.tesfirebase.app.presentation.auth.sinup.SignupScreen
import com.example.tesfirebase.ui.theme.TesFirebaseTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesFirebaseTheme {
                AuthNavigation()

            }
        }
    }
}


