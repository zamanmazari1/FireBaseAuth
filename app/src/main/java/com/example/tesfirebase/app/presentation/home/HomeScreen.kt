package com.example.tesfirebase.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // UI for the home screen

    Column (modifier = Modifier.fillMaxSize().background(Color.Red),horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center){
    Text(text = "Home Screen")


    }

}