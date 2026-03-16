package com.example.mesenger.presenatation.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController) {

    Scaffold {
        Column {
            Text("Profile Screen", color = Color.White)
        }
    }

}