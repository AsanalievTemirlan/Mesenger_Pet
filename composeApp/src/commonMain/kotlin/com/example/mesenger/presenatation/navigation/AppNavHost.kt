package com.example.mesenger.presenatation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mesenger.presenatation.screens.home.HomeScreen
import com.example.mesenger.presenatation.screens.profile.ProfileScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreen(navController)
        }
        composable<ProfileScreenRoute> {
            ProfileScreen(navController)
        }
    }

}