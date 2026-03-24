package com.example.mesenger.presenatation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mesenger.presenatation.screens.auth.RegistrationScreen
import com.example.mesenger.presenatation.screens.home.HomeScreen
import com.example.mesenger.presenatation.screens.profile.ProfileScreen
import mesenger.composeapp.generated.resources.Res
import mesenger.composeapp.generated.resources.ic_home
import mesenger.composeapp.generated.resources.ic_profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppNavHost(deepLinkUrl: String? = null) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = currentRoute != RegistrationScreenRoute::class.qualifiedName

    val tabs = listOf(HomeScreenRoute, ProfileScreenRoute)
    val icons = listOf(Res.drawable.ic_home, Res.drawable.ic_profile)

    var selectedTab by remember { mutableStateOf(0) }

    LaunchedEffect(deepLinkUrl) {
        deepLinkUrl?.let { url ->
            val fragment = url.substringAfter("#", "")
            if (fragment.isNotEmpty()) {
                val params = fragment.split("&")
                    .mapNotNull {
                        val parts = it.split("=")
                        if (parts.size == 2) parts[0] to parts[1] else null
                    }
                    .toMap()

                val accessToken = params["access_token"]
                val refreshToken = params["refresh_token"]

                if (accessToken != null && refreshToken != null) {

                    navController.navigate(HomeScreenRoute) {
                        popUpTo(RegistrationScreenRoute) { inclusive = true }
                    }
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                Column {
                    HorizontalDivider(
                        thickness = 0.5.dp,
                        color = Color.LightGray.copy(alpha = 0.3f)
                    )
                    NavigationBar(
                        containerColor = Color.White,
                        tonalElevation = 0.dp,
                        modifier = Modifier.height(60.dp)
                    ) {
                        tabs.forEachIndexed { index, route ->
                            NavigationBarItem(
                                selected = selectedTab == index,
                                onClick = {
                                    selectedTab = index
                                    navController.navigate(route) {
                                        popUpTo(0)
                                        launchSingleTop = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(icons[index]),
                                        contentDescription = null,
                                        modifier = Modifier.size(26.dp)
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    indicatorColor = Color.Transparent,
                                    selectedIconColor = Color.Black,
                                    unselectedIconColor = Color.LightGray
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = RegistrationScreenRoute,
            modifier = Modifier.padding(if (showBottomBar) innerPadding else PaddingValues(0.dp))
        ) {
            composable<HomeScreenRoute> { HomeScreen(navController) }
            composable<ProfileScreenRoute> { ProfileScreen(navController) }
            composable<RegistrationScreenRoute> { RegistrationScreen(navController) }
        }
    }
}
