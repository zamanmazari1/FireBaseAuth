package com.example.tesfirebase.app.appnavigation.auth_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.tesfirebase.app.presentation.auth.login.LoginScreen
import com.example.tesfirebase.app.presentation.auth.sinup.SignupScreen
import com.example.tesfirebase.app.presentation.home.HomeScreen

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRoutes.Login.name) {
        composable(AppRoutes.Login.name) {
            LoginScreen(navController = navController)
        }
        composable(AppRoutes.Signup.name) {
            SignupScreen(navController = navController)

        }
        composable(AppRoutes.Home.name) {
            HomeScreen(navController = navController)
        }

    }
}

enum class AppRoutes{
    Login,
    Signup,
    Home

}

